/** JSPUiEngineImpl.java.

	Purpose:
		
	Description:
		
	History:
		3:15:18 PM Mar 26, 2014, Created by jumperchen

Copyright (C) 2014 Potix Corporation. All Rights Reserved.
 */
package org.zkoss.zk.ui.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.lang.Classes;
import org.zkoss.lang.Library;
import org.zkoss.lang.Objects;
import org.zkoss.util.ArraysX;
import org.zkoss.xel.VariableResolver;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.FulfillEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.ext.Native;
import org.zkoss.zk.ui.ext.Scope;
import org.zkoss.zk.ui.ext.Scopes;
import org.zkoss.zk.ui.ext.render.PrologAllowed;
import org.zkoss.zk.ui.impl.UiEngineImpl.Extension;
import org.zkoss.zk.ui.metainfo.AttributesInfo;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.metainfo.NativeInfo;
import org.zkoss.zk.ui.metainfo.NodeInfo;
import org.zkoss.zk.ui.metainfo.PageDefinition;
import org.zkoss.zk.ui.metainfo.Property;
import org.zkoss.zk.ui.metainfo.TemplateInfo;
import org.zkoss.zk.ui.metainfo.TextInfo;
import org.zkoss.zk.ui.metainfo.VariablesInfo;
import org.zkoss.zk.ui.metainfo.ZScriptInfo;
import org.zkoss.zk.ui.metainfo.ZkInfo;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.ComponentsCtrl;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zk.ui.sys.ExecutionCtrl;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.sys.WebAppCtrl;
import org.zkoss.zk.ui.util.ComponentCloneListener;
import org.zkoss.zk.ui.util.Composer;
import org.zkoss.zk.ui.util.ComposerExt;
import org.zkoss.zk.ui.util.Condition;
import org.zkoss.zk.ui.util.ForEach;
import org.zkoss.zk.ui.util.FullComposer;
import org.zkoss.zk.ui.util.Template;
import org.zkoss.zk.xel.Evaluators;

/**
 * A UiEngineImpl class copy
 * @author jumperchen
 * @see UiEngineImpl
 */
public class JSPUiEngineImpl {
	private static final Logger log = LoggerFactory.getLogger(JSPUiEngineImpl.class);
	private volatile Extension _ext;

	// Supporting Classes//
	public static class TemplateImpl implements Template, java.io.Serializable {
		private final TemplateInfo _tempInfo;
		private final Map<String, Object> _params;
		private final String _src;

		public TemplateImpl(TemplateInfo tempInfo, Component comp) {
			_tempInfo = tempInfo;
			_params = tempInfo.resolveParameters(comp);
			_src = tempInfo.getSrc(comp);
		}

		public Component[] create(Component parent, Component insertBefore,
				VariableResolver resolver, Composer composer) {
			final Execution exec = Executions.getCurrent();
			final ExecutionCtrl execCtrl = (ExecutionCtrl) exec;
			final Component[] cs;

			if (resolver != null)
				exec.addVariableResolver(resolver);

			Page prevPage = null;
			Page page = parent != null ? parent.getPage() : null;
			final boolean fakepg = page == null;
			if (fakepg) {
				prevPage = execCtrl.getCurrentPage();
				page = new VolatilePage(prevPage);
				((PageCtrl) page).preInit();
				execCtrl.setCurrentPage(page);
			}
			try {
				cs = execCreate0(new CreateInfo(((WebAppCtrl) exec.getDesktop()
						.getWebApp()).getUiFactory(), exec, page, composer), // technically
																				// sys
																				// composer
																				// can
																				// be
																				// used
																				// but
																				// we
																				// don't
																				// (to
																				// simplify
																				// it)
						_tempInfo, parent, insertBefore);

				// Notice: if parent is not null, cs[j].page == parent.page
				if (fakepg && parent == null)
					for (int j = 0; j < cs.length; ++j)
						cs[j].detach();

				afterCreate(exec, null, cs);
			} finally {
				if (fakepg) {
					execCtrl.setCurrentPage(prevPage);
					try {
						((DesktopCtrl) exec.getDesktop()).removePage(page);
					} catch (Throwable ex) {
						log.warn("", ex);
					}
					((PageCtrl) page).destroy();
				}
				if (resolver != null)
					exec.removeVariableResolver(resolver);
			}

			final Component c2 = _src != null ? exec.createComponents(_src,
					parent, insertBefore, resolver) : null;
			return merge(cs, c2);
		}

		public Map<String, Object> getParameters() {
			return _params;
		}
	}

	private static Component[] merge(Component[] cs, Component c) {
		if (c != null) {
			cs = ArraysX.resize(cs, cs.length + 1);
			cs[cs.length - 1] = c;
		}
		return cs;
	}

	/**
	 * Called after the whole component tree has been created by this engine.
	 * 
	 * @param comps
	 *            the components being created. It is never null but it might be
	 *            a zero-length array.
	 */
	private void afterCreate(Execution exec, Component[] comps) {
		afterCreate(exec, getExtension(), comps);
	}

	private Extension getExtension() {
		if (_ext == null) {
			synchronized (this) {
				if (_ext == null) {
					String clsnm = Library
							.getProperty("org.zkoss.zk.ui.impl.UiEngineImpl.extension");
					if (clsnm != null) {
						try {
							_ext = (Extension) Classes
									.newInstanceByThread(clsnm);
						} catch (Throwable ex) {
							log.error("Unable to instantiate " + clsnm, ex);
						}
					}
					if (_ext == null)
						_ext = new DefaultExtension();
				}
			}
		}
		return _ext;
	}

	private static void afterCreate(Execution exec, Extension ext,
			Component[] comps) {
		if (ext != null)
			ext.afterCreate(comps);
	}

	private static final Component[] execCreate0(CreateInfo ci,
			NodeInfo parentInfo, Component parent, Component insertBefore) {
		final List<Component> created = new LinkedList<Component>();
		final Page page = ci.page;
		final PageDefinition pagedef = parentInfo.getPageDefinition();
		// note: don't use page.getDefinition because createComponents
		// might be called from a page other than instance's
		final ReplaceableText replaceableText = new ReplaceableText();
		for (Iterator it = parentInfo.getChildren().iterator(); it.hasNext();) {
			final Object meta = it.next();
			if (meta instanceof ComponentInfo) {
				final ComponentInfo childInfo = (ComponentInfo) meta;
				final ForEach forEach = childInfo.resolveForEach(page, parent);
				if (forEach == null) {
					if (isEffective(childInfo, page, parent)) {
						final Component[] children = execCreateChild(ci,
								parent, childInfo, replaceableText,
								insertBefore);
						for (int j = 0; j < children.length; ++j)
							created.add(children[j]);
					}
				} else {
					while (forEach.next()) {
						if (isEffective(childInfo, page, parent)) {
							final Component[] children = execCreateChild(ci,
									parent, childInfo, replaceableText,
									insertBefore);
							for (int j = 0; j < children.length; ++j)
								created.add(children[j]);
						}
					}
				}
			} else if (meta instanceof ZkInfo) {
				final ZkInfo childInfo = (ZkInfo) meta;
				final ForEach forEach = childInfo.resolveForEach(page, parent);
				if (forEach == null) {
					if (isEffective(childInfo, page, parent)) {
						final Component[] children = execCreateChild(ci,
								parent, childInfo, replaceableText,
								insertBefore);
						for (int j = 0; j < children.length; ++j)
							created.add(children[j]);
					}
				} else {
					while (forEach.next()) {
						if (isEffective(childInfo, page, parent)) {
							final Component[] children = execCreateChild(ci,
									parent, childInfo, replaceableText,
									insertBefore);
							for (int j = 0; j < children.length; ++j)
								created.add(children[j]);
						}
					}
				}
			} else if (meta instanceof TextInfo) {
				// parent must be a native component
				final String s = ((TextInfo) meta).getValue(parent);
				if (s != null && s.length() > 0)
					if (parent != null) {
						parent.insertBefore(((Native) parent).getHelper()
								.newNative(s), insertBefore);
					} else if (page != null) {
						created.add(ci.uf.newComponent(page, null, page
								.getLanguageDefinition().newLabelInfo(null, s),
								insertBefore));
					} else {
						throw new UnsupportedOperationException(
								"parent or page required for native label: "
										+ s);
					}
			} else {
				execNonComponent(ci, parent, meta);
			}
		}
		return created.toArray(new Component[created.size()]);
	}

	private static Component[] execCreateChild(CreateInfo ci, Component parent,
			ZkInfo childInfo, ReplaceableText replaceableText,
			Component insertBefore) {
		return childInfo.withSwitch() ? execSwitch(ci, childInfo, parent,
				insertBefore)
				: execCreate0(ci, childInfo, parent, insertBefore);
	}

	private static Component[] execCreateChild(CreateInfo ci, Component parent,
			ComponentInfo childInfo, ReplaceableText replaceableText,
			Component insertBefore) {
		final ComponentDefinition childdef = childInfo.getComponentDefinition();
		if (childdef.isInlineMacro()) {
			if (insertBefore != null)
				throw new UnsupportedOperationException(
						"The inline macro doesn't support template");

			final Map<String, Object> props = new HashMap<String, Object>();
			props.put("includer", parent);
			childInfo.evalProperties(props, ci.page, parent, true);
			return new Component[] { ci.exec.createComponents(
					childdef.getMacroURI(), parent, props) };
		} else {
			String rt = null;
			if (replaceableText != null) {
				rt = replaceableText.text;
				replaceableText.text = childInfo.getReplaceableText();
				if (replaceableText.text != null)
					return new Component[0];
				// Note: replaceableText is one-shot only
				// So, replaceable text might not be generated
				// and it is ok since it is only blank string
			}

			Component child = execCreateChild0(ci, parent, childInfo, rt,
					insertBefore);
			return child != null ? new Component[] { child } : new Component[0];
		}
	}

	private static Component execCreateChild0(CreateInfo ci, Component parent,
			ComponentInfo childInfo, String replaceableText,
			Component insertBefore) {
		Composer composer = childInfo.resolveComposer(ci.page, parent);
		ComposerExt composerExt = null;
		boolean bPopComposer = false;
		if (composer instanceof FullComposer) {
			ci.pushFullComposer(composer);
			bPopComposer = true;
			composer = null; // ci will handle it
		} else if (composer instanceof ComposerExt) {
			composerExt = (ComposerExt) composer;
		}

		Component child = null;
		final boolean bRoot = parent == null;
		try {
			if (composerExt != null) {
				childInfo = composerExt.doBeforeCompose(ci.page, parent,
						childInfo);
				if (childInfo == null)
					return null;
			}
			childInfo = ci.doBeforeCompose(ci.page, parent, childInfo, bRoot);
			if (childInfo == null)
				return null;

			child = ci.uf
					.newComponent(ci.page, parent, childInfo, insertBefore);

			if (replaceableText != null) {
				final Object xc = ((ComponentCtrl) child).getExtraCtrl();
				if (xc instanceof PrologAllowed)
					((PrologAllowed) xc).setPrologContent(replaceableText);
			}

			final boolean bNative = childInfo instanceof NativeInfo;
			if (bNative)
				setProlog(ci, child, (NativeInfo) childInfo);

			doBeforeComposeChildren(composerExt, child);
			ci.doBeforeComposeChildren(child, bRoot);

			execCreate(ci, childInfo, child, null); // recursive (and
													// appendChild)

			if (bNative)
				setEpilog(ci, child, (NativeInfo) childInfo);

			if (child instanceof AfterCompose)
				((AfterCompose) child).afterCompose();

			doAfterCompose(composer, child);
			ci.doAfterCompose(child, bRoot);

			ComponentsCtrl.applyForward(child, childInfo.getForward());
			// applies the forward condition
			// 1) we did it after all child created, so it may reference
			// to it child (thought rarely happens)
			// 2) we did it after afterCompose, so what specified
			// here has higher priority than class defined by application
			// developers

			// Bug ZK-504: even might be listened later (in parent's composer)
			// See also ZK-759
			Events.postEvent(new CreateEvent(Events.ON_CREATE, child, ci.exec
					.getArg()));

			return child;
		} catch (Throwable ex) {
			boolean ignore = false;
			if (composerExt != null) {
				try {
					ignore = composerExt.doCatch(ex);
				} catch (Throwable t) {
					log.error("Failed to invoke doCatch for " + childInfo, t);
				}
			}
			if (!ignore) {
				ignore = ci.doCatch(ex, bRoot);
				if (!ignore)
					throw UiException.Aide.wrap(ex);
			}

			return child != null && child.getPage() != null ? child : null;
			// return child only if attached successfully
		} finally {
			try {
				if (composerExt != null)
					composerExt.doFinally();
				ci.doFinally(bRoot);
			} catch (Throwable ex) {
				throw UiException.Aide.wrap(ex);
			} finally {
				if (bPopComposer)
					ci.popFullComposer();
			}
		}
	}

	/**
	 * Cycle 1: Creates all child components defined in the specified
	 * definition.
	 * 
	 * @return the first component being created.
	 */
	private static final Component[] execCreate(CreateInfo ci,
			NodeInfo parentInfo, Component parent, Component insertBefore) {
		String fulfillURI = null;
		if (parentInfo instanceof ComponentInfo) {
			final ComponentInfo pi = (ComponentInfo) parentInfo;
			String fulfill = pi.getFulfill();
			if (fulfill != null) { // defer the creation of children
				fulfill = fulfill.trim();
				if (fulfill.length() > 0) {
					if (fulfill.charAt(0) == '=') {
						fulfillURI = fulfill.substring(1).trim();
					} else {
						new FulfillListener(fulfill, pi, parent);
						return new Component[0];
					}
				}
			}
		}

		Component[] cs = execCreate0(ci, parentInfo, parent, insertBefore);

		if (fulfillURI != null) {
			fulfillURI = (String) Evaluators.evaluate(
					((ComponentInfo) parentInfo).getEvaluator(), parent,
					fulfillURI, String.class);
			if (fulfillURI != null) {
				cs = merge(cs, ci.exec.createComponents(fulfillURI, parent,
						insertBefore, null));
			}
		}

		return cs;
	}

	@SuppressWarnings("unchecked")
	private static final void doAfterCompose(Composer composer, Component comp)
			throws Exception {
		if (composer != null)
			composer.doAfterCompose(comp);
	}

	@SuppressWarnings("unchecked")
	/* package */static void doBeforeComposeChildren(ComposerExt composerExt,
			Component comp) throws Exception {
		if (composerExt != null)
			composerExt.doBeforeComposeChildren(comp);
	}

	/** Handles <zk switch>. */
	private static Component[] execSwitch(CreateInfo ci, ZkInfo switchInfo,
			Component parent, Component insertBefore) {
		final Page page = ci.page;
		final Object switchCond = switchInfo.resolveSwitch(page, parent);
		for (Iterator it = switchInfo.getChildren().iterator(); it.hasNext();) {
			final ZkInfo caseInfo = (ZkInfo) it.next();
			final ForEach forEach = caseInfo.resolveForEach(page, parent);
			if (forEach == null) {
				if (isEffective(caseInfo, page, parent)
						&& isCaseMatched(caseInfo, page, parent, switchCond)) {
					return execCreateChild(ci, parent, caseInfo, null,
							insertBefore);
				}
			} else {
				final List<Component> created = new LinkedList<Component>();
				while (forEach.next()) {
					if (isEffective(caseInfo, page, parent)
							&& isCaseMatched(caseInfo, page, parent, switchCond)) {
						final Component[] children = execCreateChild(ci,
								parent, caseInfo, null, insertBefore);
						for (int j = 0; j < children.length; ++j)
							created.add(children[j]);
						return created.toArray(new Component[created.size()]);
						// only once (AND condition)
					}
				}
			}
		}
		return new Component[0];
	}

	private static boolean isCaseMatched(ZkInfo caseInfo, Page page,
			Component parent, Object switchCond) {
		if (!caseInfo.withCase())
			return true; // default clause

		final Object[] caseValues = caseInfo.resolveCase(page, parent);
		for (int j = 0; j < caseValues.length; ++j) {
			if (caseValues[j] instanceof String && switchCond instanceof String) {
				final String casev = (String) caseValues[j];
				final int len = casev.length();
				if (len >= 2 && casev.charAt(0) == '/'
						&& casev.charAt(len - 1) == '/') { // regex
					if (Pattern.compile(casev.substring(1, len - 1))
							.matcher((String) switchCond).matches())
						return true;
					else
						continue;
				}
			}
			if (Objects.equals(switchCond, caseValues[j]))
				return true; // OR condition
		}
		return false;
	}

	/**
	 * Executes a non-component object, such as ZScript, AttributesInfo...
	 */
	private static final void execNonComponent(CreateInfo ci, Component comp,
			Object meta) {
		final Page page = ci.page;
		if (meta instanceof ZScriptInfo) {
			// Spec fix since 6.0.0: if/unless shall be evaluated first
			final ZScriptInfo zsInfo = (ZScriptInfo) meta;
			if (isEffective(zsInfo, page, comp)) {
				if (zsInfo.isDeferred()) {
					((PageCtrl) page).addDeferredZScript(comp,
							zsInfo.getZScript());
					// isEffective is handled later
				} else {
					final Scope scope = Scopes
							.beforeInterpret(comp != null ? (Scope) comp : page);
					try {
						page.interpret(zsInfo.getLanguage(),
								zsInfo.getContent(page, comp), scope);
					} finally {
						Scopes.afterInterpret();
					}
				}
			}
		} else if (meta instanceof AttributesInfo) {
			final AttributesInfo attrs = (AttributesInfo) meta;
			if (comp != null)
				attrs.apply(comp); // it handles isEffective
			else
				attrs.apply(page);
		} else if (meta instanceof TemplateInfo) {
//			final TemplateInfo tempInfo = (TemplateInfo) meta;
//			if (isEffective(tempInfo, page, comp))
//				comp.setTemplate(tempInfo.getName(), new TemplateImpl(tempInfo,
//						comp));
		} else if (meta instanceof VariablesInfo) {
			final VariablesInfo vars = (VariablesInfo) meta;
			if (comp != null)
				vars.apply(comp); // it handles isEffective
			else
				vars.apply(page);
		} else {
			// Note: we don't handle ComponentInfo here, because
			// getNativeContent assumes no child component
			throw new IllegalStateException(meta + " not allowed in " + comp);
		}
	}

	private static final boolean isEffective(Condition cond, Page page,
			Component comp) {
		return comp != null ? cond.isEffective(comp) : cond.isEffective(page);
	}

	/**
	 * The listener to create children when the fulfill condition is satisfied.
	 */
	private static class FulfillListener implements EventListener<Event>,
			java.io.Serializable, Cloneable, ComponentCloneListener {
		private String[] _evtnms;
		private Component[] _targets;
		private Component _comp;
		private final ComponentInfo _compInfo;
		private final String _fulfill;
		private transient String _uri;

		private FulfillListener(String fulfill, ComponentInfo compInfo,
				Component comp) {
			_fulfill = fulfill;
			_compInfo = compInfo;
			_comp = comp;

			init();

			for (int j = _targets.length; --j >= 0;)
				_targets[j].addEventListener(10000, _evtnms[j], this);
		}

		private void init() {
			_uri = null;
			final List<Object[]> results = new LinkedList<Object[]>();
			for (int j = 0, len = _fulfill.length();;) {
				int k = j;
				for (int elcnt = 0; k < len; ++k) {
					final char cc = _fulfill.charAt(k);
					if (elcnt == 0) {
						if (cc == ',')
							break;
						if (cc == '=') {
							_uri = _fulfill.substring(k + 1).trim();
							break;
						}
					} else if (cc == '{') {
						++elcnt;
					} else if (cc == '}') {
						if (elcnt > 0)
							--elcnt;
					}
				}

				String sub = (k >= 0 ? _fulfill.substring(j, k) : _fulfill
						.substring(j)).trim();
				if (sub.length() > 0)
					results.add(ComponentsCtrl.parseEventExpression(_comp, sub,
							_comp, false));

				if (_uri != null || k < 0 || (j = k + 1) >= len)
					break;
			}

			int j = results.size();
			_targets = new Component[j];
			_evtnms = new String[j];
			j = 0;
			for (Iterator<Object[]> it = results.iterator(); it.hasNext(); ++j) {
				final Object[] result = it.next();
				_targets[j] = (Component) result[0];
				_evtnms[j] = (String) result[1];
			}
		}

		public void onEvent(Event evt) throws Exception {
			for (int j = _targets.length; --j >= 0;)
				_targets[j].removeEventListener(_evtnms[j], this); // one shot
																	// only

			final Execution exec = Executions.getCurrent();
			execCreate0(new CreateInfo(((WebAppCtrl) exec.getDesktop()
					.getWebApp()).getUiFactory(), exec, _comp.getPage(), null), // technically
																				// sys
																				// composer
																				// can
																				// be
																				// used
																				// but
																				// we
																				// don't
																				// (to
																				// simplify
																				// it)
					_compInfo, _comp, null);

			if (_uri != null) {
				final String uri = (String) Evaluators.evaluate(
						_compInfo.getEvaluator(), _comp, _uri, String.class);
				if (uri != null)
					exec.createComponents(uri, _comp, null);
			}

			Events.sendEvent(new FulfillEvent(Events.ON_FULFILL, _comp, evt));
			// Use sendEvent so onFulfill will be processed before
			// the event triggers the fulfill (i.e., evt)
		}

		// ComponentCloneListener//
		public Object willClone(Component comp) {
			final FulfillListener clone;
			try {
				clone = (FulfillListener) clone();
			} catch (CloneNotSupportedException e) {
				throw new InternalError();
			}
			clone._comp = comp;
			clone.init();
			return clone;
		}
	}

	private static class ReplaceableText {
		private String text;
	}
	

	private static class DefaultExtension implements Extension {
		public void afterCreate(Component[] comps) {
		}

		public void afterRenderNewPage(Page page) {
		}

		public void afterRenderComponents(Collection<Component> comps) {
		}
	}

	// Handling Native Component//
	/**
	 * Sets the prolog of the specified native component.
	 */
	private static final void setProlog(CreateInfo ci, Component comp,
			NativeInfo compInfo) {
		final Native nc = (Native) comp;
		final Native.Helper helper = nc.getHelper();
		StringBuffer sb = null;
		final List<NodeInfo> prokids = compInfo.getPrologChildren();
		if (!prokids.isEmpty()) {
			sb = new StringBuffer(256);
			getNativeContent(ci, sb, comp, prokids, helper);
		}

		final NativeInfo splitInfo = compInfo.getSplitChild();
		if (splitInfo != null && splitInfo.isEffective(comp)) {
			if (sb == null)
				sb = new StringBuffer(256);
			getNativeFirstHalf(ci, sb, comp, splitInfo, helper);
		}

		if (sb != null && sb.length() > 0)
			nc.setPrologContent(sb.insert(0, nc.getPrologContent()).toString());
	}

	/**
	 * Sets the epilog of the specified native component.
	 * 
	 * @param comp
	 *            the native component
	 */
	private static final void setEpilog(CreateInfo ci, Component comp,
			NativeInfo compInfo) {
		final Native nc = (Native) comp;
		final Native.Helper helper = nc.getHelper();
		StringBuffer sb = null;
		final NativeInfo splitInfo = compInfo.getSplitChild();
		if (splitInfo != null && splitInfo.isEffective(comp)) {
			sb = new StringBuffer(256);
			getNativeSecondHalf(ci, sb, comp, splitInfo, helper);
		}

		final List<NodeInfo> epikids = compInfo.getEpilogChildren();
		if (!epikids.isEmpty()) {
			if (sb == null)
				sb = new StringBuffer(256);
			getNativeContent(ci, sb, comp, epikids, helper);
		}

		if (sb != null && sb.length() > 0)
			nc.setEpilogContent(sb.append(nc.getEpilogContent()).toString());
	}

	public String getNativeContent(Component comp, List<NodeInfo> children,
			Native.Helper helper) {
		final StringBuffer sb = new StringBuffer(256);
		getNativeContent(
				new CreateInfo(
						((WebAppCtrl) comp.getDesktop().getWebApp())
								.getUiFactory(),
						Executions.getCurrent(), comp.getPage(), null), sb,
				comp, children, helper);
		return sb.toString();
	}

	/**
	 * @param comp
	 *            the native component
	 */
	private static final void getNativeContent(CreateInfo ci, StringBuffer sb,
			Component comp, List<NodeInfo> children, Native.Helper helper) {
		for (NodeInfo meta : children) {
			if (meta instanceof NativeInfo) {
				final NativeInfo childInfo = (NativeInfo) meta;
				final ForEach forEach = childInfo.resolveForEach(ci.page, comp);
				if (forEach == null) {
					if (childInfo.isEffective(comp)) {
						getNativeFirstHalf(ci, sb, comp, childInfo, helper);
						getNativeSecondHalf(ci, sb, comp, childInfo, helper);
					}
				} else {
					while (forEach.next()) {
						if (childInfo.isEffective(comp)) {
							getNativeFirstHalf(ci, sb, comp, childInfo, helper);
							getNativeSecondHalf(ci, sb, comp, childInfo, helper);
						}
					}
				}
			} else if (meta instanceof TextInfo) {
				final String s = ((TextInfo) meta).getValue(comp);
				if (s != null)
					helper.appendText(sb, s);
			} else if (meta instanceof ZkInfo) {
				ZkInfo zkInfo = (ZkInfo) meta;
				if (zkInfo.withSwitch())
					throw new UnsupportedOperationException(
							"<zk switch> in native not allowed yet");

				final ForEach forEach = zkInfo.resolveForEach(ci.page, comp);
				if (forEach == null) {
					if (isEffective(zkInfo, ci.page, comp)) {
						getNativeContent(ci, sb, comp, zkInfo.getChildren(),
								helper);
					}
				} else {
					while (forEach.next())
						if (isEffective(zkInfo, ci.page, comp))
							getNativeContent(ci, sb, comp,
									zkInfo.getChildren(), helper);
				}
			} else {
				execNonComponent(ci, comp, meta);
			}
		}
	}

	/**
	 * Before calling this method, childInfo.isEffective must be examined
	 */
	private static final void getNativeFirstHalf(CreateInfo ci,
			StringBuffer sb, Component comp, NativeInfo childInfo,
			Native.Helper helper) {
		helper.getFirstHalf(sb, childInfo.getTag(),
				evalProperties(comp, childInfo.getProperties()),
				childInfo.getDeclaredNamespaces());

		final List<NodeInfo> prokids = childInfo.getPrologChildren();
		if (!prokids.isEmpty())
			getNativeContent(ci, sb, comp, prokids, helper);

		final NativeInfo splitInfo = childInfo.getSplitChild();
		if (splitInfo != null && splitInfo.isEffective(comp))
			getNativeFirstHalf(ci, sb, comp, splitInfo, helper); // recursive
	}

	/**
	 * Before calling this method, childInfo.isEffective must be examined
	 */
	private static final void getNativeSecondHalf(CreateInfo ci,
			StringBuffer sb, Component comp, NativeInfo childInfo,
			Native.Helper helper) {
		final NativeInfo splitInfo = childInfo.getSplitChild();
		if (splitInfo != null && splitInfo.isEffective(comp))
			getNativeSecondHalf(ci, sb, comp, splitInfo, helper); // recursive

		final List<NodeInfo> epikids = childInfo.getEpilogChildren();
		if (!epikids.isEmpty())
			getNativeContent(ci, sb, comp, epikids, helper);

		helper.getSecondHalf(sb, childInfo.getTag());
	}

	/**
	 * Returns a map of properties, (String name, String value).
	 */
	private static final Map<String, Object> evalProperties(Component comp,
			List<Property> props) {
		if (props == null || props.isEmpty())
			return Collections.emptyMap();

		final Map<String, Object> map = new LinkedHashMap<String, Object>(
				props.size() * 2);
		for (Property prop : props) {
			if (prop.isEffective(comp))
				map.put(prop.getName(),
						Classes.coerce(String.class, prop.getValue(comp)));
		}
		return map;
	}
}

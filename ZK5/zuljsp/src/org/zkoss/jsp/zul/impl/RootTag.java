/* RootTag.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Jul 20 19:07:04     2007, Created by tomyeh
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import java.io.StringWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.tagext.JspFragment;

import org.zkoss.util.logging.Log;
import org.zkoss.web.servlet.Charsets;
import org.zkoss.web.servlet.xel.RequestContexts;
import org.zkoss.web.servlet.http.Https;
import org.zkoss.xel.VariableResolver;
import org.zkoss.xel.XelException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Richlet;
import org.zkoss.zk.ui.RichletConfig;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.metainfo.LanguageDefinition;
import org.zkoss.zk.ui.metainfo.PageDefinitions;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.sys.ExecutionCtrl;
import org.zkoss.zk.ui.sys.UiFactory;
import org.zkoss.zk.ui.sys.RequestInfo;
import org.zkoss.zk.ui.sys.WebAppCtrl;
import org.zkoss.zk.ui.sys.SessionsCtrl;
import org.zkoss.zk.ui.sys.SessionCtrl;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.impl.Attributes;
import org.zkoss.zk.ui.impl.RequestInfoImpl;
import org.zkoss.zk.ui.http.I18Ns;
import org.zkoss.zk.ui.http.WebManager;
import org.zkoss.zk.ui.http.ExecutionImpl;

/**
 * A skeletal class to implement the root ZK tag.
 * Currently, only the page tag ({@link org.zkoss.jsp.zul.PageTag})
 * extends from this class.
 *
 * <p>The derive may override {@link #init} to initialize the
 * page.
 *
 * @author tomyeh, Ian Tsai
 */
abstract public class RootTag extends AbstractTag {
	private static final Log log = Log.lookup(RootTag.class);
	private LanguageDefinition _langdef;
	private Page _page;
	private String _zsLang = "Java";
	private String _lang = "xul/html";
	private String _cacheable;
	private List<AbstractTag> _rootCompTags;

	/**
	 * 
	 * @return the current ZK {@link Page} of this jsp page.
	 */
	public Page getPage() {
		return _page;
	}
	/**
	 * protected Constractor. Constract a RootTag with
	 * LanguageDefinition =  "xul/html".
	 *
	 */
	protected RootTag() {
		_langdef = LanguageDefinition.lookup(_lang);
		_rootCompTags = new ArrayList<AbstractTag>();
	}
	/**
	 * 
	 * @param comp
	 */
	/*pacckage*/void addRootComponent(AbstractTag comp){
		_rootCompTags.add(comp);
	}
	/** Adds a child tag.
	 */
	public void addChildTag(ComponentTag child) {
		if(child.isInline())
			for(int i=0;i<child.getComponents().length;i++)
				child.getComponents()[i].setPage(_page);
		else child.getComponent().setPage(_page);
	}
	/** Returns the default scripting language.
	 */
	public String getZScriptLanguage() {
		return _zsLang;
	}
	/**
	 * Sets the defult scripting language in this Root tag.
	 *
	 * <p>Default: Java.
	 *
	 * @param lang the name of the scripting language, such as
	 * Java, Ruby and Groovy.
	 */
	public void setZScriptLanguage(String lang) {
		_zsLang = lang != null ? lang: "Java";
	}
	/** 
	 * Sets the defult scripting language in this Root tag.
	 * It is the same as {@link #setZScriptLanguage} (used to simplify
	 * the typing in JSP page).
	 */
	public void setZscriptLanguage(String lang) {
		setZScriptLanguage(lang);
	}
	/**
	 * Get the markup language for this page.<br>
	 * The markup language determines the default component set. 
	 * Currently, it supports xul/html and xhtml.
	 * @return the markup language for this page. 
	 */
	public String getLanguage() {
		return this._lang;
	}
	/**
	 * Set the markup language for this page.<br>
	 * The markup language determines the default component set. 
	 * Currently, it supports xul/html and xhtml.
	 * @param lang the markup language for this page. 
	 */
	public void setLanguage(String lang) {
		if(lang==null||"".equals(lang))
			throw new IllegalArgumentException("language can't be null or empty!!!");
		_langdef = LanguageDefinition.lookup(_lang);
	}
	/**
	 * It specifies whether the client can cache the output.
	 * Note: Browsers, such as Firefox and IE, don't handle the cache of DHTML correctly, 
	 * so it is not safe to specify cacheable with true for Ajax devices.
	 * @return String
	 */
	public String getCacheable() {
		return _cacheable;
	}
	/**
	 * It specifies whether the client can cache the output.
	 * Note: Browsers, such as Firefox and IE, don't handle the cache of DHTML correctly, 
	 * so it is not safe to specify cacheable with true for Ajax devices.
	 * @param cacheable
	 */
	public void setCacheable(String cacheable) {
		_cacheable = cacheable;
	}
	//Derived to override//
	/** Initializes the page.
	 * It is called after the page is created, and
	 * before any component is created.
	 *
	 * <p>Default: does nothing
	 *
	 * @param exec the execution.
	 * Note: when this method is called, the execution is not activated.
	 * For example, Executions.getCurrent() returns null.
	 * @param page the page
	 */
	protected void init(Execution exec, Page page) {
	}
	

	//super//
	/** To process this root tag.
	 * The deriving class rarely need to override this method.
	 */
	public void doTag() throws JspException, IOException {
		if (!isEffective())
			return; //nothing to do

		final AbstractTag pt =
			(AbstractTag)findAncestorWithClass(this, AbstractTag.class);
		if ((pt instanceof RootTag) || (pt instanceof BranchTag))
			throw new JspTagException("<page> can be placed inside of "+pt);

		final JspContext jspctx = getJspContext();
		final PageContext pgctx = Jsps.getPageContext(jspctx);
		final ServletContext svlctx = pgctx.getServletContext();
		final HttpServletRequest request =
			(HttpServletRequest)pgctx.getRequest();
		final HttpServletResponse response =
			(HttpServletResponse)pgctx.getResponse();

		final WebManager webman = WebManager.getWebManager(svlctx);
		final Session sess = WebManager.getSession(svlctx, request);

		RequestContexts.push(new PageRequestContext(pgctx));
			//Optiional but enable JSP page use DPS's TLD files
			//If we don't push, everying works fine except JSP page
			//that uses ZK JSP tags cannot use xxx.dsp.tld
		SessionsCtrl.setCurrent(sess);
		Object old = I18Ns.setup(sess, request, response, "UTF-8");		
		try {
			final WebApp wapp = sess.getWebApp();
			final WebAppCtrl wappc = (WebAppCtrl)wapp;

			final String path = Https.getThisServletPath(request);
			final Desktop desktop =
				webman.getDesktop(sess, request, response, path, true);
			final RequestInfo ri = new RequestInfoImpl(
				wapp, sess, desktop, request,
				PageDefinitions.getLocator(wapp, path));
			((SessionCtrl)sess).notifyClientRequest(true);

			final UiFactory uf = wappc.getUiFactory();
			final Richlet richlet = new MyRichlet();
			_page = uf.newPage(ri, richlet, path);
			if(_zsLang!=null)_page.setZScriptLanguage(_zsLang);

			final Execution exec = new ExecutionImpl(
				svlctx, request, response, desktop, _page);
			final ZkProxy.Proxy zkproxy = ZkProxy.getProxy();
			zkproxy.setPageOnly(exec);
				//Always use include; not forward

			//We have to set header here since setPageOnly will cause
			//PageCtrl.redraw not to set them at all
			final boolean cacheable = (_cacheable!=null)?
					Boolean.getBoolean(_cacheable) : 
					exec.getDesktop().getDevice().isCacheable();
			if(!cacheable) {
				try {
					exec.setResponseHeader("Pragma", "no-cache");
					exec.addResponseHeader("Cache-Control", "no-cache");
					exec.addResponseHeader("Cache-Control", "no-store");
					exec.setResponseHeader("Expires", "-1");
					zkproxy.setAttribute(exec, Attributes.NO_CACHE, Boolean.TRUE);
				} catch (Throwable ex) { //ignore it
				}
			}

			init(exec, _page); //initialize the page
			Jsps.setZkPageObject(jspctx, _page);

			zkproxy.beforeRender(exec, _page);
			try {
				wappc.getUiEngine().execNewPage(exec, richlet, _page, jspctx.getOut());
			} finally {
				zkproxy.afterRender(exec, _page);
			}
		} finally {
			SessionsCtrl.setCurrent((Session)null);
			RequestContexts.pop();
			I18Ns.cleanup(request, old);
		}
	}

	
	private class MyRichlet implements Richlet {
		
		//@Override
		public void init(RichletConfig config) {
		}
		//@Override
		public void destroy() {
		}
		//@Override
		public void service(Page page) {
			VariableResolvers vResolvs = VariableResolvers.getInstance(getJspContext());
			try {
				vResolvs.initPageVariableResolvers(_page);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Initiators inits = Initiators.getInstance(getJspContext());
			Initiator[] sysinits = null;
			if (page != null) {
				final WebApp wapp = page.getDesktop().getWebApp();
				String currentVersion = wapp.getVersion();
				if(org.zkoss.util.Utils.compareVersion(
						org.zkoss.util.Utils.parseVersion(currentVersion),
						org.zkoss.util.Utils.parseVersion("5.0.7")) > -1) {
					sysinits = wapp.getConfiguration().getInitiators();
				}
			}
			if(inits!=null)inits.doInit(page, sysinits);
			
			try {
				final StringWriter out = new StringWriter();
				JspFragment frag = getJspBody();
				if (frag != null)
					frag.invoke(out);
				if(inits!=null)inits.doAfterCompose(page, _rootCompTags);
				if (frag != null)
					Utils.adjustChildren(
						page, null, page.getRoots(), out.toString());
			} catch (Exception ex) {
				log.realCauseBriefly(ex); //Apache Jasper Compiler eats ex
				if(inits!=null)inits.doCatch(ex);
				throw UiException.Aide.wrap(ex);
			} finally{
				if(inits!=null)inits.doFinally();
			}
		}
		//@Override
		public LanguageDefinition getLanguageDefinition() {
			return _langdef;
		}
	}
	/**
	 * Root tag was supposed to handle all children's ZScript.
	 * @param parent The owner of zscript segment. 
	 * @param zs A ZScript object.
	 * @throws IOException  
	 */
	public void processZScript(Component parent, ZScript zs) 
	throws IOException {
		processZScript(_page, parent, zs);
	}

	/**
	 * A useful method use to invoke zscript. 
	 * @param page page instance.
	 * @param parent The owner of zscript segment. 
	 * @param zs A ZScript object.
	 * @throws IOException
	 */
	public static void processZScript(Page page, Component parent, ZScript zs) 
	throws IOException {
		if (zs.getLanguage() == null)
			zs.setLanguage(page.getZScriptLanguage());
		
		if (zs.isDeferred())
			((PageCtrl)page).addDeferredZScript(parent, zs);
		else
			ZkProxy.getProxy().interpret(page, parent, zs);
	}

}

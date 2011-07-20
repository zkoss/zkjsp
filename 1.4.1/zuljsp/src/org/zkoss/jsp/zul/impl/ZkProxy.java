/* ZkProxy.java

	Purpose:
		
	Description:
		
	History:
		Wed Oct  7 11:11:30     2009, Created by tomyeh

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

This program is distributed under GPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.jsp.zul.impl;

import java.util.Map;
import java.util.HashMap;
import java.io.Writer;
import java.io.IOException;

import javax.servlet.ServletRequest;

import org.zkoss.lang.Classes;
import org.zkoss.zk.Version;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.ext.Scope;
import org.zkoss.zk.ui.ext.Scopes;
import org.zkoss.zk.ui.impl.Attributes;

/**
 * A proxy used to access ZK functions that depends on ZK versions.
 *
 * @author tomyeh
 * @since 1.4.0
 */
public class ZkProxy {
	private static Proxy _proxy;

	/** Returns the ZK proxy used to access version-dependent features.
	 */
	public static Proxy getProxy() {
		if (_proxy == null) {//no need to synchronized
			try {
				Classes.forNameByThread("org.zkoss.zk.ui.sys.PageRenderer");
				_proxy = newProxy5();
			} catch (ClassNotFoundException ex) {
				_proxy = newProxy3();
			}
		}
		return _proxy;
	}

	/** Interface to access version-dependent features of ZK.
	 */
	public static interface Proxy {
		/** Interprets the zscript.
		 */
		public void interpret(Page page, Component comp, ZScript zs);
		/** Sets the page information.
		 */
		public void setPageOnly(Execution exec);
		/** Adds the mold to the specified component definition.
		 */
		public void addMold(ComponentDefinition compdef,
		String widgetClass, String moldName, String moldURI);
		/** Prepares the rendering of a page.
		 */
		public void beforeRender(Execution exec, Page page);
		/** Cleans up the rendering of a page.
		 */
		public void afterRender(Execution exec, Page page);
		/** Returns an execution attribute.
		 */
		public Object getAttribute(Execution exec, String name);
		/** Sets an execution attribute.
		 */
		public void setAttribute(Execution exec, String name, Object value);
		/** Removes an execution attribute.
		 */
		public void removeAttribute(Execution exec, String name);		
		/** return the inline component.
		 */
		public Inline newInline(String txt);
	}

	private static final String ENTRY_COUNT = "org.zkoss.zk.jsp.entryCount";
	/** Returns true if it is the first enter.
	 */
	private static boolean enter(Proxy proxy, Execution exec) {
		Integer cnt = (Integer)proxy.getAttribute(exec, ENTRY_COUNT);
		proxy.setAttribute(exec, ENTRY_COUNT, cnt != null ? cnt + 1: 1);
		return cnt == null || cnt == 0;
	}
	/** Returns true if it is the last exit (paired with the first enter).
	 */
	private static boolean exit(Proxy proxy, Execution exec) {
		Integer cnt = (Integer)proxy.getAttribute(exec, ENTRY_COUNT);
		proxy.setAttribute(exec, ENTRY_COUNT, cnt != null ? cnt - 1: 0);
		return cnt == null || cnt <= 1;
	}
	/**
	 * 
	 * @return A proxy instance for zk5
	 */
	private static Proxy newProxy5() {
		return new Proxy() {
			public void interpret(Page page, Component comp, ZScript zs) {
				final Scope scope =
					Scopes.beforeInterpret(comp != null ? (Scope)comp: page);
				try {
					page.interpret(zs.getLanguage(),
						zs.getContent(page, comp), scope);
				} finally {
					Scopes.afterInterpret();
				}
			}
			public void setPageOnly(Execution exec) {
				setAttribute(exec,
					Attributes.PAGE_REDRAW_CONTROL, "page");
			}
			public void addMold(ComponentDefinition compdef,
			String widgetClass, String moldName, String moldURI) {
				if (widgetClass != null)
					compdef.setDefaultWidgetClass(widgetClass);
				if (moldURI != null && moldURI.length() != 0)
					throw new UnsupportedOperationException("moldURI not supported in 5.0 or later");
			}
			public void beforeRender(Execution exec, Page page) {
				if (enter(this, exec))
					setAttribute(exec, Attributes.PAGE_RENDERER,
						new PageRenderer());
			}
			public void afterRender(Execution exec, Page page) {
				if (exit(this, exec)) {
					removeAttribute(exec, Attributes.PAGE_REDRAW_CONTROL);
					removeAttribute(exec, Attributes.PAGE_RENDERER);
				}
			}
			public Object getAttribute(Execution exec, String name) {
				return exec.getAttribute(name);
			}
			public void setAttribute(Execution exec, String name, Object value) {
				exec.setAttribute(name, value);
			}
			public void removeAttribute(Execution exec, String name) {
				exec.removeAttribute(name);
			}		
			public Inline newInline(String txt){
				return new Zk5Inline(txt);				
			}
		};
	}
	/**
	 * 
	 * @return A proxy instance for zk3 and previous version.
	 */
	private static Proxy newProxy3() {
		return new Proxy() {
			/** @deprecated */
			public void interpret(Page page, Component comp, ZScript zs) {
				final Map backup = new HashMap();
				final org.zkoss.zk.scripting.Namespace ns = comp != null ?
					org.zkoss.zk.scripting.Namespaces.beforeInterpret(backup, comp, false):
					org.zkoss.zk.scripting.Namespaces.beforeInterpret(backup, page, false);
				try {	
					page.interpret(zs.getLanguage(),
						zs.getContent(page, comp), ns);
				} finally {
					org.zkoss.zk.scripting.Namespaces.afterInterpret(backup, ns, false);
				}
			}
			/** @deprecated */
			public void setPageOnly(Execution exec) {
				setAttribute(exec,
					PageCtrl.ATTR_REDRAW_BY_INCLUDE, Boolean.TRUE);
			}
			/** @deprecated */
			public void addMold(ComponentDefinition compdef,
			String widgetClass, String moldName, String moldURI) {
				if (moldURI != null && moldURI.length() != 0) {
					compdef.addMold(
						moldName == null || moldName.length() == 0 ? "default": moldName,
							moldURI.startsWith("class:") ? moldURI: moldURI, null);
				}
				if (widgetClass != null && widgetClass.length() != 0)
					throw new UnsupportedOperationException("widgetClass not supported in "+Version.UID);
			}
			public void beforeRender(Execution exec, Page page) {
				enter(this, exec);
			}
			/** @deprecated */ //so javac won't show warning
			public void afterRender(Execution exec, Page page) {
				if (exit(this, exec))
					removeAttribute(exec, PageCtrl.ATTR_REDRAW_BY_INCLUDE);
			}
			public Object getAttribute(Execution exec, String name) {
				return ((ServletRequest)exec.getNativeRequest()).getAttribute(name);
			}
			public void setAttribute(Execution exec, String name, Object value) {
				((ServletRequest)exec.getNativeRequest()).setAttribute(name, value);
					//can't access setAttribute directly, since signature of ZK 5 changed
			}
			public void removeAttribute(Execution exec, String name) {
				((ServletRequest)exec.getNativeRequest()).removeAttribute(name);
					//can't access removeAttribute directly, since signature of ZK 5 changed
			}
			public Inline newInline(String txt){
				return new Inline(txt);				
			}
		};
	}
}

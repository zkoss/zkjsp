/* JspFactoryWrapper.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/13 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;




/**
 * @author Ian Tsai
 *
 */
public abstract class AbstractJspFactory extends JspFactory {
	
	
	/** The factory to wrap. */
	protected JspFactory _factory;
	protected PageContextInitiator pageContextInitiator;
	/**
	 * @param factory
	 */
	protected AbstractJspFactory(JspFactory factory, PageContextInitiator initiator) {
		if (factory == null)
			throw new NullPointerException("factory");
		if (factory instanceof AbstractJspFactory)
			throw new IllegalArgumentException("Don't wrap twice");
		//System.out.println(">>> "+this.getClass()+"::construct...");
		_factory = factory;
		pageContextInitiator = initiator;
	}
	
	public PageContext getPageContext(Servlet servlet,
	ServletRequest request, ServletResponse response,
	String errorPageURL, boolean needsSession,
	int bufferSize, boolean autoflush) {
		//System.out.println(">>> "+this.getClass()+"::getPageContext()");
		
		final PageContext pc = _factory.getPageContext(
			servlet, request, response,
			errorPageURL, needsSession, bufferSize, autoflush);
		return pageContextInitiator.initPageContext(pc);
	}
	
    public void releasePageContext(PageContext pc) {
    	//System.out.println(">>> "+this.getClass()+"::releasePageContext()");
    	_factory.releasePageContext(pc);
    }
    public JspEngineInfo getEngineInfo() {
    	//System.out.println(">>> "+AbstractJspFactory.class.getSimpleName()+"::getEngineInfo()");
    	return _factory.getEngineInfo();
    }
    public boolean equals(Object obj) {
		return _factory.equals(obj);
	}
	public int hashCode() {
		return _factory.hashCode();
	}
	public String toString() {
		return _factory.toString();
	}
}

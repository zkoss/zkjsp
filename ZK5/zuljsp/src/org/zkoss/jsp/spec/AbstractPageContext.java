/* FakePageContext.java

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

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.ErrorData;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ExpressionEvaluator;
import javax.servlet.jsp.el.VariableResolver;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * @author Ian Tsai
 *
 */
public abstract class AbstractPageContext extends PageContext {

	protected PageContext context;

	/**
	 * 
	 * @param context
	 */
	public AbstractPageContext(PageContext context) {
		super();
		this.context = context;
	}

	public boolean equals(Object obj) {
		return context.equals(obj);
	}
	public Object findAttribute(String arg0) {
		return context.findAttribute(arg0);
	}
	public void forward(String arg0) throws ServletException, IOException {
		context.forward(arg0);
	}
	public Object getAttribute(String arg0, int arg1) {
		return context.getAttribute(arg0, arg1);
	}
	public Object getAttribute(String arg0) {
		return context.getAttribute(arg0);
	}
	public Enumeration getAttributeNamesInScope(int arg0) {
		return context.getAttributeNamesInScope(arg0);
	}
	public int getAttributesScope(String arg0) {
		return context.getAttributesScope(arg0);
	}
	public ErrorData getErrorData() {
		return context.getErrorData();
	}
	public Exception getException() {
		return context.getException();
	}
	public ExpressionEvaluator getExpressionEvaluator() {
		return context.getExpressionEvaluator();
	}
	public JspWriter getOut() {
		return context.getOut();
	}
	public Object getPage() {
		return context.getPage();
	}
	public ServletRequest getRequest() {
		return context.getRequest();
	}
	public ServletResponse getResponse() {
		return context.getResponse();
	}
	public ServletConfig getServletConfig() {
		return context.getServletConfig();
	}
	public ServletContext getServletContext() {
		return context.getServletContext();
	}
	public HttpSession getSession() {
		return context.getSession();
	}
	public VariableResolver getVariableResolver() {
		return context.getVariableResolver();
	}
	public void handlePageException(Exception arg0) throws ServletException,
			IOException {
		context.handlePageException(arg0);
	}
	public void handlePageException(Throwable arg0) throws ServletException,
			IOException {
		context.handlePageException(arg0);
	}
	public int hashCode() {
		return context.hashCode();
	}
	public void include(String arg0, boolean arg1) throws ServletException,
			IOException {
		context.include(arg0, arg1);
	}
	public void include(String arg0) throws ServletException, IOException {
		context.include(arg0);
	}
	public void initialize(Servlet arg0, ServletRequest arg1,
			ServletResponse arg2, String arg3, boolean arg4, int arg5,
			boolean arg6) throws IOException, IllegalStateException,
			IllegalArgumentException {
		context.initialize(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}
	public JspWriter popBody() {
		return context.popBody();
	}
	public BodyContent pushBody() {
		return context.pushBody();
	}
	public JspWriter pushBody(Writer writer) {
		return context.pushBody(writer);
	}
	public void release() {
		context.release();
	}
	public void removeAttribute(String arg0, int arg1) {
		context.removeAttribute(arg0, arg1);
	}
	public void removeAttribute(String arg0) {
		context.removeAttribute(arg0);
	}
	public void setAttribute(String arg0, Object arg1, int arg2) {
		context.setAttribute(arg0, arg1, arg2);
	}
	public void setAttribute(String arg0, Object arg1) {
		context.setAttribute(arg0, arg1);
	}
	public String toString() {
		return context.toString();
	}

	

}

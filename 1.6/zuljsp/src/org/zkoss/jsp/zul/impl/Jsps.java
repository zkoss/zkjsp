/* Jsps.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Mon Jul 23 14:11:09     2007, Created by tomyeh
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.JspException;

import org.zkoss.zk.ui.Page;

/**
 * Jsp Utilities.
 *
 * @author tomyeh, Ian Tsai
 */
public class Jsps {
	private Jsps() {}

	
	/** Reteurns the page context of the specified JSP context.
	 */
	public static final PageContext getPageContext(JspContext jspctx)
	throws JspException {
		if (jspctx instanceof PageContext)
			return (PageContext)jspctx;
		try {
			final PageContext pgctx = (PageContext)
				jspctx.getExpressionEvaluator().evaluate(
				"${pageContext}", PageContext.class, null, null);
			if (pgctx != null)
				return pgctx;
			throw new JspException("Unable to retrieve PageContext from "+jspctx);
		} catch (javax.servlet.jsp.el.ELException ex) {
			throw new JspException("Unable to retrieve PageContext from "+jspctx, ex);
		}
	}
	private static final String PAGE_KEY = Page.class.getName()+"!KEY";
	/**
	 * Store ZK Page Object into JspContext 
	 * @param jspctx
	 * @return Page
	 */
	public static final Page getZkPageObject(JspContext jspctx)
	{
		return (Page) jspctx.getAttribute(PAGE_KEY);
	}
	/**
	 * get ZK Page Object from JspContext
	 * @param jspctx
	 * @param page
	 */
	public static final void setZkPageObject(JspContext jspctx, Page page)
	{
		jspctx.setAttribute(PAGE_KEY, page);
	}
}

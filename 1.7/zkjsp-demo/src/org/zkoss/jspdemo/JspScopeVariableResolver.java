/* HttpRequestVariableResolver.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/28 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jspdemo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.zkoss.xel.VariableResolver;
import org.zkoss.xel.XelException;

/**
 * @author Ian Tsai
 *
 */
public class JspScopeVariableResolver implements VariableResolver {
	
	private HttpServletRequest reguest;
	private HttpSession session;
	
	public JspScopeVariableResolver(HttpServletRequest req)
	{
		reguest = req;
	}
	public JspScopeVariableResolver(HttpSession sess)
	{
		session = sess;
	}

	/* Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 * 3.
	 */
	public Object resolveVariable(String name) throws XelException {
		if(reguest!=null)
			return reguest.getAttribute(name);
		if(session!=null)
			return session.getAttribute(name);
		return null;
	}

}

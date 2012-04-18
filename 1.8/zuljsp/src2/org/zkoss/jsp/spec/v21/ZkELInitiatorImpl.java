/* ZkELInitiator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2010/3/14 2010, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec.v21;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;

import org.zkoss.jsp.spec.ZkELInitiator;

/**
 * @author Ian Tsai
 *
 */
public class ZkELInitiatorImpl implements ZkELInitiator{
	
	/*
	 * (non-Javadoc)
	 * @see org.zkoss.jsp.spec.ZkELInitiator#init(javax.servlet.ServletContext)
	 */
	public void init(ServletContext servletCotext) {
		JspFactory aJspFac = JspFactory.getDefaultFactory();
		JspApplicationContext jspAppContext = 
			aJspFac.getJspApplicationContext(servletCotext);
		jspAppContext.addELResolver(new ZkELResolverImpl());
	}
	

}

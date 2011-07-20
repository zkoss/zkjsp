/* JspFactoryContextListener.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/14 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

import org.zkoss.lang.Classes;
import org.zkoss.lang.SystemException;

/** A listener to initialize JSP factory such that the user can access
 * ZK built-in varibles in EL.
 * @author Ian Tsai
 */
public class JspFactoryContextListener implements ServletRequestListener {	
	
	static volatile boolean haveInit = false;
	
	public void requestDestroyed(ServletRequestEvent servRequestEvt) {		
	}

	public void requestInitialized(ServletRequestEvent servRequestEvt) {
	/* find out if this container is Jsp2.1 container or jsp2.0 container.
	 * 1. Find out if interface JspApplicationContext(Since JSP 2.1) is exist.
	 * 2. If so, use v21 JspFactory
	 * 3. Use v20 JspFactory otherwise. 
	 */
		if (haveInit) return;
		try {
			Classes.forNameByThread("javax.servlet.jsp.JspApplicationContext");
			initJspFactory("org.zkoss.jsp.spec.v21.JspFactoryImpl");
		} catch (ClassNotFoundException e) {
			initJspFactory("org.zkoss.jsp.spec.v20.JspFactoryImpl");
		}
		haveInit = true;
	}
	
	private void initJspFactory(String facClassName) {
		try {
			Classes.forNameByThread(facClassName);
		} catch (ClassNotFoundException e) {
			throw new SystemException(e);
		}
	}
}

/* EnvironmentValidateListener.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2011/11/11  14:40:07, Created by benbai
}}IS_NOTE

Copyright (C) 2011 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.zkoss.jsp.Version;;

public class EnvironmentValidateListener implements ServletContextListener {
	/**
	 * do all environment validation here and show exception as need
	 */
	public void contextInitialized(ServletContextEvent event) {
		try {
			int v = org.zkoss.util.Utils.parseVersion(org.zkoss.zk.Version.RELEASE)[0];
			if (v < 6)
				throw new Exception(" ZK 6.0 or later is required for ZK JSP "+Version.UID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
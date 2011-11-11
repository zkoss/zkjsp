package org.zkoss.jsp.zul.impl;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;

import org.zkoss.zk.Version;

public class EnvironmentValidateListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			int v = org.zkoss.util.Utils.parseVersion(Version.RELEASE)[0];
			if (v == 6)
				throw new Exception(" ZK 6.0 or later is required for ZK JSP 2.0 or later");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
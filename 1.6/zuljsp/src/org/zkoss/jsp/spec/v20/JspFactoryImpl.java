/* JspFactoryImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Apr  8 11:07:01     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec.v20;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.JspEngineInfo;
import javax.servlet.jsp.PageContext;

import org.zkoss.jsp.spec.AbstractJspFactory;
import org.zkoss.jsp.spec.AbstractPageContext;
import org.zkoss.jsp.spec.PageContextInitiator;

import org.zkoss.util.logging.Log;

/**
 * To intercept how a JSP page is rendered.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 */
public class JspFactoryImpl extends AbstractJspFactory {
	private static final Log log = Log.lookup(JspFactoryImpl.class);
	/**
	 * 
	 * @param factory
	 * @param initiator
	 */
	protected JspFactoryImpl(JspFactory factory, PageContextInitiator initiator) {
		super(factory, initiator);
	}

	static{
		try{
//			System.out.println(">>> ZK Jsp spec v2.0 JspFactory initializing...");
			JspFactory.setDefaultFactory(
				new JspFactoryImpl(JspFactory.getDefaultFactory(), new PageContextInitiator(){
					public PageContext initPageContext(PageContext oriContext) {
						return new ZkPageContextImpl(oriContext);
					}
				} ));//end of class...
//			System.out.println(">>> ZK Jsp spec v2.0  JspFactory initialized.");
		}catch(Throwable e){
			log.error(e);
		}
	}

}

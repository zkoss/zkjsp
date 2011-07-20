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
package org.zkoss.jsp.spec.v21;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.zkoss.jsp.spec.AbstractJspFactory;
import org.zkoss.jsp.spec.PageContextInitiator;

import org.zkoss.util.logging.Log;

/**
 * To intercept how a JSP page is rendered.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 */
public class JspFactoryImpl extends AbstractJspFactory {
	private static final Log log = Log.lookup(JspFactoryImpl.class);

	private static final String JSP_APP_CONTEXT_KEY = 
		JspApplicationContext.class.getCanonicalName()+":JSP_APP_CONTEXT_KEY";
	
	
	protected JspFactoryImpl(JspFactory factory, PageContextInitiator initiator) {
		super(factory, initiator);
	}


	static{
		try{
			JspFactory.setDefaultFactory(
				new JspFactoryImpl(JspFactory.getDefaultFactory(), new PageContextInitiator(){
					public PageContext initPageContext(PageContext oriContext) {
						return oriContext;
					}
				}) );//end of class...
//			log.info("JSP Version "+fac.getEngineInfo().getSpecificationVersion());
		}catch(Throwable e){
			log.error(e);
		}
	}

	
	public JspApplicationContext getJspApplicationContext(ServletContext context) {
		if (context == null)
			throw new NullPointerException("context");

		JspApplicationContext impl = _factory.getJspApplicationContext(context);
		
		if (context.getAttribute(JSP_APP_CONTEXT_KEY)==null) {
			impl.addELResolver(new ZkELResolverImpl());
			context.setAttribute(JSP_APP_CONTEXT_KEY, JSP_APP_CONTEXT_KEY);
		}
		
		return impl;
	}


}

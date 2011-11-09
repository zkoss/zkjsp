/* VariableResolvers.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/18 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspContext;

import org.zkoss.zk.ui.Page;

/**
 * @author Ian Tsai
 *
 */
public class VariableResolvers {
	
	private static final String KEY = VariableResolvers.class.getName()+"!KEY";
	
	private final List<VariableResolverInfo> varResInfoList;
	private final JspContext context;

	
	/**
	 * 
	 * @param context
	 */
	public VariableResolvers(JspContext context) {
		super();
		this.context = context;
		varResInfoList = new LinkedList<VariableResolverInfo>();
	}

	/**
	 * get VariableResolvers instance from JspContext
	 * @param context the place to store Initiators. 
	 * @return VariableResolvers
	 */
	public static VariableResolvers getInstance(JspContext context)
	{
		VariableResolvers vres = 
			(VariableResolvers) context.getAttribute(KEY);
		if(vres==null)
			context.setAttribute(KEY, vres = new VariableResolvers(context));
		return vres;
	}
	
	/**
	 *  Add new initiator and it's args into this handler. 
	 * @param reso
	 */
	public void add(VariableResolverInfo reso){
		varResInfoList.add(reso);
	}
	
	/**
	 * create VariableResolvers using VariableResolverInfo, and attach them to the page.
	 * @param page a page that will be attached with VariableResolvers. 
	 * @throws Exception if any bad things happened while  VariableResolver construction.
	 */
	public void initPageVariableResolvers( Page page) throws Exception{
		
		for(VariableResolverInfo vrInfo: varResInfoList)
			page.addVariableResolver(vrInfo.newVariableResolver());
	}
	/**
	 * 
	 * @return JspContext
	 */
	public JspContext getContext() {
		return context;
	}

}

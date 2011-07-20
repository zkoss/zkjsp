/* FunctionMappers.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/19 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspContext;

import org.zkoss.xel.Function;
import org.zkoss.xel.FunctionMapper;
import org.zkoss.xel.VariableResolver;
import org.zkoss.xel.XelException;
import org.zkoss.zk.ui.Page;

/**
 * @author Ian Tsai
 *
 */
public class FunctionMappers {
	
	private static final String KEY = FunctionMappers.class.getName()+"!KEY";
	private final Map<String,Function> funcMap;
	private final JspContext context;
	/**
	 * 
	 * @param context
	 */
	public FunctionMappers(JspContext context) {
		super();
		this.context = context;
		funcMap = new LinkedHashMap<String, Function>();
	}

	/**
	 * get VariableResolvers instance from JspContext
	 * @param context the place to store Initiators. 
	 * @return FunctionMappers
	 */
	public static FunctionMappers getInstance(JspContext context)
	{
		FunctionMappers fms = 
			(FunctionMappers) context.getAttribute(KEY);
		if(fms==null)
			context.setAttribute(KEY, fms = new FunctionMappers(context));
		return fms;
	}
	/**
	 * 
	 * @param prefix
	 * @param name
	 * @param func
	 */
	public void addFunction(String prefix, String name, Function func)
	{
		if (name == null || name.length() == 0 || func == null)
			throw new IllegalArgumentException();
		funcMap.put(prefix + ":" + name, func);
	}
	/**
	 * 
	 * @param page
	 */
	public void atachFunctions(Page page)
	{
		page.addFunctionMapper(new FunctionMapper(){
			/*Implementation Logic(non-Javadoc)
			 * 1.
			 * 2.
			 * 3.
			 */
			public Function resolveFunction(String prefix, String name)
					throws XelException {
				return (Function)funcMap.get(prefix+":"+name);
			}
			public Collection getClassNames() {
				return null;
			}
			public Class resolveClass(String name) throws XelException {
				return null;
			}
		});//end of class...
	}

}

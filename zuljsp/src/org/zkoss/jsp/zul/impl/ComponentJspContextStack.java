/* ComponentJspContext.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/17 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import java.util.LinkedList;
import java.util.Stack;

import javax.servlet.jsp.JspContext;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.xel.impl.ExecutionResolver;

/**
 * @author Ian Tsai
 *
 */
public class ComponentJspContextStack {
	
	private static final String COMPONENT_STORE_KEY = Component.class.getName()+"!COMPONENT_STORE_KEY";

	/**
	 * push current self component.
	 * @param jspCtx
	 * @param comp
	 */
	public static void push(JspContext jspCtx, Component comp)
	{
		if(jspCtx==null || comp==null)
			throw new IllegalArgumentException("jspCtx or ns can't be null!");
		getStack(jspCtx).push(comp);
	}
	/**
	 * pop current self component.
	 * @param jspCtx
	 * @return Component
	 */
	public static Component pop(JspContext jspCtx)
	{
		return getStack(jspCtx).pop();
	}
	/**
	 * 
	 * @param jspCtx
	 * @return Component
	 */
	public static Component peakCurrent(JspContext jspCtx)
	{
		Stack<Component> stack = getStack(jspCtx);
		if(stack.isEmpty())
			return null;
		return stack.peek();
	}
	/**
	 * 
	 * @param jspCtx
	 * @return Stack
	 */
	private static Stack<Component> getStack(JspContext jspCtx)
	{
		Stack<Component> stack = 
			(Stack<Component>) jspCtx.getAttribute(COMPONENT_STORE_KEY);
		if(stack==null)
			jspCtx.setAttribute(COMPONENT_STORE_KEY, stack = new Stack<Component>());
		return stack;
	}
}

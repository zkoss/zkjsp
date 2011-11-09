/* ZkPageContextImpl.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/13 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec.v20;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.VariableResolver;

import org.zkoss.jsp.spec.AbstractPageContext;
import org.zkoss.jsp.spec.JspVariableResolver;
import org.zkoss.jsp.zul.impl.ComponentJspContextStack;
import org.zkoss.jsp.zul.impl.Jsps;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.xel.impl.ExecutionResolver;

/**
 * @author Ian Tsai
 *
 */
public class ZkPageContextImpl extends AbstractPageContext{
	
	
	private VariableResolver varResolver;
	/**
	 * 
	 * @param context
	 */
	public ZkPageContextImpl(final PageContext context) {
		super(context);
		varResolver =  new VariableResolver(){
			JspVariableResolver innerResolver = new JspVariableResolver(context);
			public Object resolveVariable(String name) throws ELException {
				Object ans = innerResolver.resolveVariable(name);
				
				if(ans ==null){
					ans = context.getVariableResolver().resolveVariable(name);
				}
				return ans;
			}
		};
	}
	
	public VariableResolver getVariableResolver() {
		return varResolver;
	}

}

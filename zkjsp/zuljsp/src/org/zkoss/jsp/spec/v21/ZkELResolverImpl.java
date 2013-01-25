/* ELResolverImpl.java

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
package org.zkoss.jsp.spec.v21;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.PropertyNotWritableException;
import javax.servlet.jsp.JspContext;

import org.zkoss.jsp.spec.ZkJspExecutionVars;
import org.zkoss.zk.ui.Executions;

/**
 * @author Ian Tsai
 *
 */
public class ZkELResolverImpl extends ELResolver {

	public ZkELResolverImpl() {}
	/**
	 * 
	 * @param ctx
	 * @param name
	 * @return
	 */
	private Object resolveVariable(final ELContext ctx, String name)
	{
		JspContext jspCtx = (JspContext)ctx.getContext(JspContext.class);
		try {
			return ZkJspExecutionVars.resolveVariable(name, jspCtx);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ELException(e);
		}
	}
	/**
	 * 
	 * @return
	 */
	private static boolean isExecutable() {
		return Executions.getCurrent()!= null;
	}


	/*Implementation Logic(non-Javadoc)
	 * 1.check if inside Zk Execution
	 * 2. retrieve value from ZK Execution, set property resolved true if there's value.
	 */
	public Object getValue(ELContext ctx, Object base, Object property)
	throws PropertyNotFoundException, ELException {
		if (ctx == null)
			throw new IllegalArgumentException();
		
		if(!isExecutable()) return null;
		
		if (base==null && property != null) {
			Object obj = resolveVariable(ctx, property.toString());
			if(obj!=null){
				ctx.setPropertyResolved(true);
				return obj;
			}
		}
		return null;
	}

	public Class<?> getType(ELContext ctx, Object base, Object property)
	throws PropertyNotFoundException, ELException {
		if (ctx == null)
			throw new IllegalArgumentException();

		if(!isExecutable())return null;
		if (base==null && property != null) {
			Object obj = resolveVariable(ctx, property.toString());
			if (obj!=null) {
				ctx.setPropertyResolved(true);
				return obj.getClass();
			}
		}
		return null;
	}

	public void setValue(ELContext ctx, Object base, Object property, Object value)
			throws PropertyNotFoundException, PropertyNotWritableException, ELException {
		
		//TODO: do nothing?
	}

	public boolean isReadOnly(ELContext ctx, Object base, Object property)
	throws PropertyNotFoundException, ELException {
		//TODO: is that simple?
		if (ctx == null)
			throw new IllegalArgumentException();
		
		if (base == null) return true;
		return false;
	}
	
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext ctx, Object base) {
		//TODO: ARE YOU SURE?
		return null;
	}
	/**
	 * 
	 */
	public Class<?> getCommonPropertyType(ELContext ctx, Object base) {
		//TODO: ..............................
		if (base==null) return String.class;
		return null;
	}
}

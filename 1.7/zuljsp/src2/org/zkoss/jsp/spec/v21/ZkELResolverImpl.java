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

import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELException;
import javax.el.ELResolver;
import javax.el.PropertyNotFoundException;
import javax.el.PropertyNotWritableException;
import javax.servlet.jsp.JspContext;

import org.zkoss.jsp.spec.JspVariableResolver;
import org.zkoss.zk.ui.Executions;

/**
 * @author Ian Tsai
 *
 */
public class ZkELResolverImpl extends ELResolver {

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
			return new JspVariableResolver(jspCtx).resolveVariable(name);
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
	/**
	 * 
	 */
	public ZkELResolverImpl() {
	}

	/*Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 * 3.
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
	/*Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 * 3.
	 */
	public Class getType(ELContext ctx, Object base, Object property)
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
	/* Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 * 3.
	 */
	public void setValue(ELContext ctx, Object base, Object property,
	Object value)
	throws PropertyNotFoundException, PropertyNotWritableException, ELException {
	}
	/*
	 * Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 * 3.
	 */
	public boolean isReadOnly(ELContext ctx, Object base, Object property)
	throws PropertyNotFoundException, ELException {
		if (ctx == null)
			throw new IllegalArgumentException();

		if (base == null) return true;
		return false;
	}
	/*
	 * Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 * 3.
	 */
	public Iterator getFeatureDescriptors(ELContext ctx, Object base) {
		return null;
	}
	/**
	 * 
	 */
	public Class getCommonPropertyType(ELContext ctx, Object base) {
		if (base==null) return String.class;
		return null;
	}
}

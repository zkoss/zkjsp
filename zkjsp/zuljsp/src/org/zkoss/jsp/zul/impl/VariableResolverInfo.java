/* VariableResolverInfo.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/28 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import java.util.Collection;

import org.zkoss.lang.Classes;
import org.zkoss.xel.VariableResolver;
import org.zkoss.zk.ui.UiException;

/**
 * A definition of the variable resolver ({@link VariableResolver}).
 *
 * <p>Note: we resolve the class by use of Classes.forNameByThread.
 * In other words, it doesn't support the class defined in zscript.
 * Why not? Since there is no way to run zscript before the init directive
 * (and better performance).
 * </p>
 * @author Ian Tsai
 *
 */
public class VariableResolverInfo {

	private final Class _resolver;
	/** The arguments, never null (might with zero length). */
	private final Object[] _args;
	/**
	 * 
	 * @param cls
	 * @param args
	 */
	public VariableResolverInfo(Class cls, Collection args) {
		checkClass(cls);
		_resolver = cls;
		_args = args.toArray();
	}
	private static void checkClass(Class cls) {
		if (!VariableResolver.class.isAssignableFrom(cls))
			throw new UiException(VariableResolver.class+" must be implemented: "+cls);
	}

	/** Creates and returns the variable resolver for the specified page.
	 */
	public VariableResolver newVariableResolver()
	throws Exception {
		return (VariableResolver)Classes.newInstance(_resolver, _args);
			
	}
}

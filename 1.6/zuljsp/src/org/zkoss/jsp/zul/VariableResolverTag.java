/* VariableResolverTag.java

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
package org.zkoss.jsp.zul;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.VariableResolverInfo;
import org.zkoss.jsp.zul.impl.VariableResolvers;
import org.zkoss.lang.Classes;
import org.zkoss.xel.VariableResolver;


/**
 * 
 * <p>&lt;zk:variable-resolver class="..." [arg0="..."] [arg1="..."] [arg2="..."] [arg3="..."]/>
 * 
 * <p>Specifies the variable resolver that will be used by the zscript interpreter to resolve 
 * unknown variables. The specified class must implement the {@link org.zkoss.xel.VariableResolver} 
 * interface. 
 * <p>You can specify multiple variable resolvers with multiple variable-resolver directives. 
 * The later declared one has higher priority. Notice that the variable-resolver directives 
 * are evaluated before the init directives, so the zscript codes referenced by the init 
 * directives are affected by the variable resolver.<br> 
 * <p>The following is an example when using ZK with the Spring framework. It resolves Java Beans
 * declared in the Spring framework, such that you access them directly.
 * 
 * <p>&lt;zk:variable-resolver class="org.zkoss.zkplus.spring.DelegatingVariableResolver"/><br>
 *  
 * @author Ian Tsai
 *
 */
public class VariableResolverTag extends AbstractTag implements DynamicAttributes{
	
	private  Class _resolverClass;
	private List<Object> _args = new LinkedList<Object>();
	private boolean flag;
	
	
	/* Implementation Logic(non-Javadoc)
	 * 1. Use VariableResolverInfo to store resolver's Class and arguments
	 * 2. pass it to VariableResolvers
	 */
	public void doTag() throws JspException, IOException {
		VariableResolverInfo vInfo = new VariableResolverInfo(_resolverClass, _args);
		VariableResolvers.getInstance(this.getJspContext()).add(vInfo);
	}
	
	private static final Pattern ARGS_ptn = Pattern.compile("arg[0-9]+");
	
	
	private static boolean isArg(String attrName){
		Matcher match = ARGS_ptn.matcher(attrName);
		return match.find();
	}
	
	/*Implementation Logic(non-Javadoc)
	 * 1. check localname length > 4, the characters after 3 must be int number.
	 * 2. add value to list in right index.
	 */
	public void setDynamicAttribute(String uri, String localName, Object value)
			throws JspException {
		try {			
			if(!isArg(localName))
				throw new IllegalArgumentException("illegal argument Name:"+localName);

			String nm = localName.substring(3);
			int index = Integer.parseInt(nm);
			_args.add(index, value);
		}
		catch(NumberFormatException e1){
			throw new IllegalArgumentException("illegal argument Name:"+localName);
		}
	}
	
	/**
	 * set the class that implements {@link  VariableResolver}.
	 * @param clazz
	 */
	public void setUse(String clazz) {
		if (clazz == null || clazz.length() == 0)
			throw new IllegalArgumentException("null or empty");
		try {
			_resolverClass =  Classes.forNameByThread(clazz);
		} catch (Exception ex) {
			throw new IllegalArgumentException("Failed to instantiate "+clazz, ex);
		}
	}
	/**
	 * Returns the class that implements {@link  VariableResolver}.
	 */
	public String getUse() {
		return _resolverClass != null ? _resolverClass.getName(): null;
	}

	
	
	
	
}

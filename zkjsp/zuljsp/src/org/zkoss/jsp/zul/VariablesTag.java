/* VariablesTag.java

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
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;
import org.zkoss.zk.ui.ext.Scope;

/**
 * @author Ian Tsai
 * @deprecated since ZK 5.0 variable is not recommended to use anymore.
 *
 */
public class VariablesTag extends AbstractTag implements DynamicAttributes {
	
	
	private BranchTag _branchTag;
	private PageTag _pageTag;
	private HashMap _vars = new HashMap();
	private boolean local;
	

	/** @deprecated &lt;variables&gt; is deprecated as release of ZK 5.0.
	 */	
	public void doTag() throws JspException, IOException {
		if(!isEffective()) return;
		
		for (Iterator it = _vars.entrySet().iterator(); it.hasNext();) {
			final Map.Entry me = (Map.Entry)it.next();
			final String name = (String)me.getKey();
			final Object value = me.getValue();
			if(_branchTag!=null)
				_branchTag.getComponent().getSpaceOwner().setAttribute(name, value, !local);
			else 
				_pageTag.getPage().setAttribute(name, value, !local);
		}
	}
	
	//SimpleTagSupport//
	/** Sets the parent tag.
	 * Deriving class rarely need to invoke this method.
	 */
	public void setParent(JspTag parent) {
		super.setParent(parent);
		if (parent instanceof BranchTag) 
			_branchTag = (BranchTag)parent;
		else if(parent instanceof PageTag)
			_pageTag = (PageTag)parent;
		else 
			throw new IllegalJspTagException("Parent tag is not a valid ZK container Tag: "+this);
	}
	
	/* Implementation Logic(non-Javadoc)
	 * 1.
	 * 2.
	 */
	public void setDynamicAttribute(String uri, String localName, Object value)
			throws JspException {
		if(localName.equals("local"))
			local = Boolean.parseBoolean(value.toString());
		else
			_vars.put(localName, value);
	}
	
	//Object//
	public String toString() {
		final StringBuffer sb = new StringBuffer(40).append("[variables]");
		if (_vars != null)
			for (Iterator it = _vars.keySet().iterator(); it.hasNext();)
				sb.append(' ').append(it.next());
		return sb.append(']').toString();
	}
}

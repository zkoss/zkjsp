/* TemplateTag.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Feb 03 10:39:09     2012, Created by Ben Bai
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.util.Map;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;
import org.zkoss.lang.CommonException;
import org.zkoss.zk.ui.Component;

/**
 * A Jsp Tag class to handle the zscript element.
 * This tag should be declared under {@link PageTag} or any Component tags.
 * 
 * @author Ian Tsai 
 */
public class TemplateTag extends AbstractTag implements DynamicAttributes {
	private BranchTag _parent;
	private String _name;

	/**
	 * 
	 * if this "attribute" represents a event Add self contents to parent's dynamic attribute.
	 *  
	 *   
	 */
	public void doTag() throws JspException, IOException {
		throw new UnsupportedOperationException("please use jsp template instead");
	}

	//SimpleTagSupport//
	/** Sets the parent tag.
	 * Deriving class rarely need to invoke this method.
	 */
	public void setParent(JspTag parent) {
		super.setParent(parent);
		if (parent instanceof BranchTag) {
			_parent = (BranchTag)parent;
		} else {
			throw new IllegalJspTagException("Parent tag is not a valid ZK container Tag: "+this);
		}
	}
	public void setName (String name) {
		_name = name;
	}
	public String getName () {
		return _name;
	}
	/**
	 * 
	 * Evaluate all attributes.
	 * If(is annotations)
	 * @param target the target component
	 * @param attrs
	 * @throws ModificationException
	 * @throws NoSuchMethodException
	 */
	protected void evaluateDynaAttributes(Component target, Map attrs) 
	throws CommonException, NoSuchMethodException{

	}
	/**
	 * Test if the attribute are annotation or component attribute.<br>
	 * If(is Component attribute)Invokes setter method to update all 
	 * assigned attribute.
	 * If(is annotation)
	 * @param target
	 * @param attnm
	 * @param value
	 * @throws ModificationException
	 * @throws NoSuchMethodException
	 */
	public static void evaluateDynaAttribute(Component target, String attnm, Object value)
	throws CommonException, NoSuchMethodException
	{
		
	}
	
	/**
	 *   Called when a tag declared to accept dynamic attributes is passed an 
	 *   attribute that is not declared in the Tag Library Descriptor.<br>
	 *   
	 * @param uri the namespace of the attribute, always null currently.
	 * @param localName the name of the attribute being set.
	 * @param value  the value of the attribute
	 */
	public void setDynamicAttribute(String uri, String localName, Object value) 
	throws JspException {
		
	}
}
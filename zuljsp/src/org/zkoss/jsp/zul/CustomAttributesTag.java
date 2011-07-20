/**
 * 
 */
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;
import org.zkoss.zk.ui.Component;

/**
 *  * Same meanings as zul <code>&slt;custom-attributes></code> Tag.<br> 
 * it's parent must be a Component Tag.
 * @author ian
 *
 */
public class CustomAttributesTag extends AbstractTag  implements DynamicAttributes  {

	private BranchTag _branchTag;
	private PageTag _pageTag;
	private HashMap _custAttrs = new HashMap();
	private String scope;
	
	/**
	 *  Add self contents to parent's dynamic attribute. 
	 */
	public void doTag() throws JspException, IOException {
		if(!isEffective()) return;
		AttributesInfo attrInfo = new AttributesInfo(_custAttrs, scope);
		if(_branchTag!=null)
			attrInfo.apply( _branchTag.getComponent());
		else 
			attrInfo.apply(_pageTag.getPage());
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
	/*
	 * current version ignor uri but if one day need to support mutiple Component set then we shall reopen it.
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute
	 * (java.lang.String, java.lang.String, java.lang.Object)
	 */
	public void setDynamicAttribute(String uri, String localName, Object value) 
	throws JspException {
		if(localName.equals("scope"))
			scope = (String)value;
		else _custAttrs.put(localName, value);
	}
	
	//Object//
	public String toString() {
		final StringBuffer sb = new StringBuffer(40).append("[custom-attributes:");
		if (_custAttrs != null)
			for (Iterator it = _custAttrs.keySet().iterator(); it.hasNext();)
				sb.append(' ').append(it.next());
		return sb.append(']').toString();
	}
	
}//end of class...

/**
 * 
 */
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;
import org.zkoss.util.ModificationException;

/**
 * Same meanings as zul attribute Tag.<br> 
 * it's parent must be a Component Tag.
 * @author Ian Y.T Tsai(zanyking)
 */
public class AttributeTag extends AbstractTag {
	
	private BranchTag _parent;
	private String _name;
	private boolean trim;
	
	/**
	 * 
	 * if this "attribute" represents a event Add self contents to parent's dynamic attribute.
	 *  
	 *   
	 */
	public void doTag() throws JspException, IOException {
		if(!isEffective()) return;
		StringWriter out = new StringWriter();
		getJspBody().invoke(out);
		String outStr = trim ? out.toString().trim() : out.toString();
		try {//because parent is a BranchTag, evaluate its attribute.
			if(_name.startsWith("on"))
				_parent.setDynamicAttribute(null, _name, outStr);
			else
				BranchTag.evaluateDynaAttribute(_parent.getComponent(), _name, outStr);
		} catch (ModificationException e) {
			throw new JspException(e);
		} catch (NoSuchMethodException e) {
			throw new JspException(e);
		}
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
	/**
	 * set attribute's key property 
	 * @param name
	 */
	public void setName(String name) {
		this._name = name;
	}
	/**
	 * 
	 * @return String
	 */
	public String getTrim() {
		return ""+trim;
	}
	/**
	 * 
	 * @param trim
	 */
	public void setTrim(String trim) {
		this.trim = "true".equalsIgnoreCase(trim);
	}

}

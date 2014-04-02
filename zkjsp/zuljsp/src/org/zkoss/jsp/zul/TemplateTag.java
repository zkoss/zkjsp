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
import java.io.StringWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.sys.WebAppCtrl;

/**
 * A Jsp Tag class to handle the template element.
 * 
 * @author jumperchen
 */
public class TemplateTag extends AbstractTag implements DynamicAttributes {
	private BranchTag _parent;
	private StringBuilder _attrs = new StringBuilder();
	private String _name;

	public void doTag() throws JspException, IOException {
		if (!isEffective())
			return;
		if (_parent != null) {

			final StringWriter out = new StringWriter();
			if (getJspBody() != null)
				getJspBody().invoke(out);

			String result = out.toString();
			int start = result.indexOf("<");
			int end = result.indexOf(":");
			if (end < start) {
				throw new IllegalArgumentException("The template syntax may be wrong :[" + result + "]");
			}
			String prefix = result.substring(start + 1, end + 1);
			if (prefix.contains(" ")) {
				throw new IllegalArgumentException("The template syntax may be wrong :[" + result + "]");
			}
			result = result.replace(prefix, "");
			String tmp = "<template " + _attrs.toString() + ">"+ result + "</template>";
			
			((WebAppCtrl) _parent.getComponent().getDesktop().getWebApp()).getUiEngine()
				.createComponents(Executions.getCurrent(), Executions.getCurrent()
						.getPageDefinitionDirectly(tmp , "zul"), null,
					_parent.getComponent(), null, null, null);
		}
	}

	// SimpleTagSupport//
	/**
	 * Sets the parent tag. Deriving class rarely need to invoke this method.
	 */
	public void setParent(JspTag parent) {
		super.setParent(parent);
		if (parent instanceof BranchTag) {
			_parent = (BranchTag) parent;
		} else {
			throw new IllegalJspTagException(
					"Parent tag is not a valid ZK container Tag: " + this);
		}
	}

	public void setDynamicAttribute(String uri, String localName, Object value)
			throws JspException {
		_attrs.append(localName).append("=\"").append(value).append("\" ");
		if ("name".equals(localName)) {
			_name = value.toString();
		}
	}
}
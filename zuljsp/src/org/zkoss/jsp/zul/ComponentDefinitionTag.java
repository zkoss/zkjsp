/* MacroDefinitionTag.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed December 09 14:50:37     2007, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.zkoss.jsp.zul.impl.ZkProxy;
import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.impl.ComponentDefinitionImpl;

/**
 * 
 * Used to define a ZK custom component in Jsp environment.<br>
 * 
 * @author Ian Tsai
 * 
 */
public class ComponentDefinitionTag  extends AbstractTag implements DynamicAttributes {
	
	private String _macroURI;
	private String _extends;
	private String _useClass;
	private String _name;
	private boolean _inline;
	private String _moldName;
	private String _moldURI;
	private String _widgetClass;
	private final Map _params = new LinkedHashMap();
	
	/*
	 * register self to JspContext.
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	public void doTag() throws JspException, IOException {
		Map macros = (Map)getJspContext().getAttribute(Const.CONTEXT_KEY);
		if(macros ==null)
			getJspContext().setAttribute(Const.CONTEXT_KEY, macros = new LinkedHashMap());
		macros.put(_name,this);
	}
	
	/**
	 * 
	 * @param page
	 */
	public void registComponentDefinition(Page page) {
		ComponentDefinition compdef;
		if (_macroURI != null) {
			compdef = page.getLanguageDefinition().getMacroDefinition(_name, _macroURI, _inline, null);
			
			if(!isEmpty(_useClass)){
				if (_inline)
					throw new UiException("class not allowed with _inline macros, ");
				compdef.setImplementationClass(_useClass);
			}
		}else if(_extends != null){
			final ComponentDefinition ref = page.getLanguageDefinition()
			.getComponentDefinition(_extends);
			if (ref.isMacro())
				throw new UiException("Unable to extend from a macro component, "+_extends);
			compdef = ref.clone(null, _name);
			if (!isEmpty(_useClass)) {
				compdef.setImplementationClass(_useClass);
					//Resolve later since might defined in zscript
			}
		}else{
			if (isEmpty(_useClass))
				throw new UiException(" The Macro-define's property: 'use' cannot be empty! ");
			final ComponentDefinitionImpl cdi =
				new ComponentDefinitionImpl(page.getLanguageDefinition(), null, _name, (Class)null);
			cdi.setCurrentDirectory(null);
				//mold URI requires it
			compdef = cdi;
			compdef.setImplementationClass(_useClass);
		}

		page.getComponentDefinitionMap().add(compdef);
		ZkProxy.getProxy().addMold(compdef, _widgetClass, _moldName, _moldURI);

		for (Iterator e = _params.entrySet().iterator(); e.hasNext();) {
			final Map.Entry me = (Map.Entry)e.next();
			compdef.addProperty((String)me.getKey(), (String)me.getValue());
		}
	}
	/*
	 * (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute
	 * (java.lang.String, java.lang.String, java.lang.Object)
	 */
	public void setDynamicAttribute(String uri, String attrName, Object value) 
	throws JspException {
		if(attrName.equals("class"))
			_useClass = (String) value;
		else 
			_params.put(attrName, value);
	}

	public String getExtends() {
		return _extends;
	}

	public void setExtends(String extendz) {
		this._extends = extendz;
	}

	public boolean getInline() {
		return _inline;
	}

	public void setInline(boolean inline) {
		_inline = inline;
	}

	public String getMacroURI() {
		return _macroURI;
	}

	public void setMacroURI(String macroURI) {
		_macroURI = macroURI;
	}

	public String getMoldName() {
		return _moldName;
	}

	public void setMoldName(String moldName) {
		_moldName = moldName;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}
	/** Whether a string is null or empty. */
	private static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}


	public String getMoldURI() {
		return _moldURI;
	}
	public void setMoldURI(String moldURI) {
		_moldURI = moldURI;
	}

	public String getWidgetClass() {
		return _widgetClass;
	}
	public void setWidgetClass(String widgetClass) {
		_widgetClass = widgetClass;
	}
}//end of class...

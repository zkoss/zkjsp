/* ZscriptTag.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Jul 27 17:09:09     2007, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;
import org.zkoss.jsp.zul.impl.Jsps;
import org.zkoss.jsp.zul.impl.RootTag;
import org.zkoss.util.logging.Log;
import org.zkoss.util.resource.ClassLocator;
import org.zkoss.web.util.resource.ServletContextLocator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.metainfo.ZScript;

/**
 * A Jsp Tag class to handle the zscript element.
 * This tag should be declared under {@link PageTag} or any Component tags.
 * 
 * @author Ian Tsai 
 */
public class ZScriptTag extends AbstractTag {
	private static final Log log = Log.lookup(ZScriptTag.class);

	private String _lang = null;
	private Component _parent;
	private RootTag _roottag;
	private boolean _deferred;
	private String _src;

	/* 
	 * Add body content to parent's zscript info.
	 * Implementation Logic(non-Javadoc)
	 * 1. If _src is nothing, use tag content to build zscript.
	 * 2. Else tag should use the resource which indicated by _src to construct the zscript. 
	 * 3. If both _src & tag content is nothing, just return.
	 */
	public void doTag() throws JspException, IOException {
		if(!super.isEffective())return;
		
		String lang = (_lang==null) ? _roottag.getZScriptLanguage() : _lang;
		ZScript zscript = null;

		if( _src==null || _src.equals("") )
		{
			// BUG 1997938: getJspBody() could be null!
			// If both content and _src are nothing, this zscript tag is nothing too.
			if(getJspBody()==null) return; 
			
			StringWriter out = new StringWriter();
			getJspBody().invoke(out);
			zscript = new ZScript(lang,out.toString());
		}
		else 
		{
			PageContext pgctx = Jsps.getPageContext(getJspContext());
			URL url = pgctx.getServletContext().getResource(_src);
			zscript = new ZScript(lang, url);
		}
		_roottag.processZScript(_parent, zscript, isDeferred());
	}

	
	//SimpleTagSupport//
	/** Sets the parent tag.
	 * Deriving class rarely need to invoke this method.
	 */
	public void setParent(JspTag parent) {
		super.setParent(parent);
		final AbstractTag pt =
		(AbstractTag)findAncestorWithClass(this, AbstractTag.class);
		if (pt instanceof RootTag) { //root component tag
			_roottag = (RootTag)pt;
		} else if (pt instanceof BranchTag) {
			BranchTag ptag = (BranchTag)pt;
			_roottag = ptag.getRootTag();
			_parent = ptag.getComponent();
		} else {
			throw new IllegalStateException("Must be nested inside the page tag: "+this);
		}
	}
	/**
	 * Returns whether to defer the execution of this zscript.
	 * <p>Default: false.
	 */
	public boolean isDeferred() {
		return _deferred;
	}
	/**
	 * Sets whether to defer the execution of this zscript.
	 * @param deferred whether to defer the execution.
	 */
	public void setDeferred(boolean deferred) {
		this._deferred  = deferred;
	}

	/** Returns the name of the scripting language in this ZScript tag.
	 *
	 * <p>Default: null (use what is defined in {@link PageTag#getZScriptLanguage}).
	 * @return the name of the scripting language in this ZScript tag. 
	 */
	public String getLanguage() {
		return _lang;
	}
	/**
	 * Sets the name of the scripting language in this ZScript tag.
	 *
	 * <p>Default: Java.
	 *
	 * @param lang the name of the scripting language, such as
	 * Java, Ruby and Groovy.
	 */
	public void setLanguage(String lang) {
		this._lang = lang;
	}


	public String getSrc() {
		return _src;
	}


	public void setSrc(String src) {
		this._src = src;
	}

}

/* InitTag.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed August 08 15:30:37     2007, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.Initiators;
import org.zkoss.jsp.zul.impl.Jsps;
import org.zkoss.jsp.zul.impl.RootTag;
import org.zkoss.lang.Classes;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zk.ui.util.Initiator;

/**
 * 
 * A Tag map to the ZK {@link Initiator}.
 * Implemented by an initiator that will be invoked if it is specified
 * in the init directive.
 *
 * <p>&lt;zk:init class="MyInit" /&gt;
 *
 * <p>Once specified, an instance inside this tag is created and {@link Initiator#doInit} is called
 * before the page is evaluated. Then, {@link Initiator#doAfterCompose} is called
 * after all components are created, and before any event is processed.
 * In additions, {@link Initiator#doFinally} is called
 * after the page has been evaluated. If an exception occurs, {@link Initiator#doCatch}
 * is called.
 *
 * <p>A typical usage: starting a transaction in doInit, rolling back it
 * in {@link Initiator#doCatch} and commit it in {@link Initiator#doFinally}
 * (if {@link Initiator#doCatch} is not called).
 *
 * @author Ian Tsai
 *
 */
public class InitTag extends AbstractTag implements DynamicAttributes{
	private LinkedHashMap _args = new LinkedHashMap();
	private String _zscript;
	private String _class;
	/**
	 *   Called when a tag declared to accept dynamic attributes is passed an 
	 *   attribute that is not declared in the Tag Library Descriptor.<br>
	 *   
	 *   In this InitTag implementation, this method is used to "add" args.<br>
	 *   For Example:<br>
	 *   <p>&lt;z:init use="demo.MyInit" arg0="an arg" arg1="another arg " ...../&gt;
	 *   
	 * @param uri the namespace of the attribute. Ignored in this version.
	 * @param localName the name of the attribute being set.
	 * @param value  the value of the attribute
	 */
	public void setDynamicAttribute(String uri, String localName, Object value)
	throws JspException {

		
		if(localName.equals("class")){
			_class = (String) value;
		}else {
			
			//start to support any argument in 2010/11/26 (by Ian Tsai)
			
//			if(!localName.startsWith("arg"))
//				throw new IllegalArgumentException("Declared attribute:"+localName+
//						" is illegal. Please use arg[int] instead.");
			//_args.add(Integer.parseInt(localName.substring(3)), value); 
			_args.put(localName, value);	
		}
		
	}
	
	/**
	 *  Add this Initiator into HttpRequest, this will be processed by Component 
	 *  container: {@link PageTag}.   
	 */
	public void doTag() throws JspException, IOException {
		if(!isEffective())return;
		
		if( !(_zscript==null ^ _class==null) )
			throw new IllegalArgumentException("Both zscript & class attributes are null or asigned values.");
		
		Initiator init = null;
		if(_class!=null)
		{
			try {
				 init = (Initiator) Classes.forNameByThread(_class).newInstance();
			} catch (Exception ex) {
				throw new IllegalArgumentException("Failed to instantiate "+_class, ex);
			}
		}
		else
		{// zscript initiator...
			init = new Initiator(){
				/* Implementation Logic(non-Javadoc)
				 * 1.
				 * 2.
				 * 3.
				 */
				public void doInit(Page page, Map args) throws Exception {
					final String zslang = page.getZScriptLanguage();
					final URL url = Jsps.getPageContext(
							getJspContext()).getServletContext().getResource(_zscript);
					if (url == null) 
						throw new UiException("File not found: "+_zscript);
					ZScript zs = new ZScript(null, zslang, url, null);
					RootTag.processZScript(page, null, zs);
				}
				public void doAfterCompose(Page page) throws Exception {}
				public void doFinally() throws Exception {}
				public boolean doCatch(Throwable ex) throws Exception {
					return false;
				}
			};//end of class...
			
		}
		Initiators.getInstance(this.getJspContext()).addInitiator(init, _args);	
	}

	/**
	 * Sets the class that implements {@link  Initiator}.
	 * @param clazz a class name  with derived class which is implements {@link Initiator}  
	 * @throws IllegalArgumentException if input class can't be found or is not implement Initiator
	 */
	public void setUse(String clazz) {
		_class = clazz;
	}
	/**
	 * Returns the class that implements {@link  Initiator}.
	 */
	public String getUse() {
		return _class;
	}
	/**
	 * 
	 * @return String
	 */
	public String getZscript() {
		return _zscript;
	}
	/**
	 * 
	 * @param zscript
	 */
	public void setZscript(String zscript) {
		this._zscript = zscript;
	}
}

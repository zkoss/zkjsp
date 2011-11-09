/**
 * 
 */
package org.zkoss.jsp.zul;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.JspTag;

import org.zkoss.jsp.zul.impl.AbstractTag;
import org.zkoss.jsp.zul.impl.BranchTag;

/**
 *  * Same meanings as zul <code>&slt;custom-attributes></code> Tag.<br> 
 * it's parent must be a Component Tag.
 * @author Ian Y.T Tsai(zanyking)
 *
 */
public class CustomAttributesTag extends AbstractTag  implements DynamicAttributes  {

	private BranchTag _branchTag;
	private PageTag _pageTag;
	private HashMap _custAttrs = new HashMap();
	private String _scope;
	
	/**
	 * @author Ian Y.T Tsai(zanyking)
	 * used to handle different composite type[map|list].
	 */
	private abstract class CompositeConverter{
		public Map convert(Map attrs){
			HashMap ans = new HashMap();
			for(Object obj : attrs.entrySet()){
				Entry entry = (Entry) obj;
				ans.put(entry.getKey(), 
						handleAttr(entry.getValue()));
			}
			return ans;
		}
		protected abstract Object handleAttr(Object value);
	}
	private CompositeConverter _composite = new CompositeConverter(){
		public Map convert(Map attr){
			return attr;
		}
		public Object handleAttr(Object value) {return null;}
	};
	
	
	/**
	 *  Add self contents to parent's dynamic attribute. 
	 */
	public void doTag() throws JspException, IOException {
		if(!isEffective()) return;
		//TODO: according to composite do corresponding works.
		
		AttributesInfo attrInfo = 
			new AttributesInfo(_composite.convert(_custAttrs), _scope);
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
		if(localName.equals("scope")){
			_scope = (String)value;
		}else if(localName.equals("composite")){
			if("list".equals(value)){
				_composite = new CompositeConverter(){
					protected Object handleAttr(Object value) {
						try{
							String[] arr = value.toString().split("[,]");
							ArrayList list = new ArrayList(arr.length);
							for(String str : arr){
								list.add(str);
							}
							return list;							
						}catch(Exception e){
							throw wrapError("list",(String) value, e);
						}
					}
				};
			}else if("map".equals(value)){
				_composite = new CompositeConverter(){
					protected Object handleAttr(Object value) {
						try{
							String[] arr = value.toString().split("[,]");
							HashMap map = new HashMap();
							String[] pair;
							for(String str : arr){
								pair = str.split("[=]");
								map.put(pair[0], pair[1]);
							}
							return map;	
						}catch(Exception e){
							throw wrapError("map",(String) value, e);
						}
					}
				};
			}
		}
		else _custAttrs.put(localName, value);
	}
	
	private static IllegalArgumentException wrapError(String composite, String input, Exception e){
		return new IllegalArgumentException("in custom-attributes composite["+composite+"], illformat of input:"+input, e);
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

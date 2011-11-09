/* IllegalJspTagException.java

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

/**
 * @author Ian Tsai
 * Indicate that there are some  
 */
public class IllegalJspTagException extends RuntimeException {
	
	/**
	 * 
	 */
	public IllegalJspTagException(){}
	/**
	 * 
	 * @param mesg
	 */
	public IllegalJspTagException(String mesg){
		super(mesg);
	}
	/**
	 * 
	 * @param e
	 */
	public IllegalJspTagException(Exception e){
		super(e);
	} 
	

}

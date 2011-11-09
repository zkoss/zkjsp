/* ZkELInitiator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2010/11/03 2010, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec;

import javax.servlet.ServletContext;

/**
 * @author Ian Tsai
 *
 */
public interface ZkELInitiator {

	/**
	 * initialize ZK's EL Variable resolver and set it into the 
	 * ServletContext(JspApplicationContext).   
	 * @param servletCotext
	 */
	public void init(ServletContext servletCotext);
}

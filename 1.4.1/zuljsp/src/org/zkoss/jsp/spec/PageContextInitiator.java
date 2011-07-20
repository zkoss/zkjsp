/* PageContextInitiator.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/14 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.spec;

import javax.servlet.jsp.PageContext;

/**
 * @author Ian Tsai
 *
 */
public interface PageContextInitiator {
	
	/**
	 * 
	 * @param oriContext
	 * @return PageContext
	 */
	PageContext initPageContext(PageContext oriContext);

}

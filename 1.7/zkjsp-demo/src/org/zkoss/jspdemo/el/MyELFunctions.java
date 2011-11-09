/* MyELFunctions.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2008/3/19 2008, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jspdemo.el;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ian Tsai
 *
 */
public class MyELFunctions {
	
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	/**
	 * This method is used to test xel-method.
	 * @param date
	 * @return
	 */
	public static String getDateString(Date date)
	{
		return FORMAT.format(date);
	}

}

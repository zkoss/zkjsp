/* PreProcessor.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jul 26, 2007 3:45:58 PM 2007, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */
package tool;

import java.io.PrintStream;
import java.util.*;

/**
 * @author ian
 * 
 */
public class PreProcessor {

	private String prefix;
	private String postfix;
	private Map<String, String> varMap;

	/**
	 * 
	 * @param prefix
	 * @param postfix
	 */
	public PreProcessor(String prefix, String postfix) {
		super();
		this.prefix = prefix;
		this.postfix = postfix;
		varMap = new HashMap<String, String>(5);
	}

	/**
	 * 
	 * @param key
	 * @param value
	 */
	public void setVariable(String key, String value) {
		varMap.put(key, value);
	}

	/**
	 * 
	 * @param arg
	 * @return
	 */
	public void preProcess(PrintStream st, String arg) {
		StringBuffer out = new StringBuffer("");
		String key, value;
		for (int i = 0, j = 0, k = 0; i < arg.length();) {

			j = arg.indexOf(prefix, i);
			if (j >= 0) {
				out.append(arg.substring(i, j));
				k = arg.indexOf(postfix, j);
				if (k < 0)
					System.out.print("How could this happend? k=" + k + " i="
							+ i + " j=" + j + " Arg=" + arg);
				key = arg.substring(j + prefix.length(), k);
				if (key.startsWith("!"))
					out.append(varMap.get(key.substring(1)).toLowerCase());
				else
					out.append(varMap.get(key));

				i = k + postfix.length();
			} else {
				out.append(arg.substring(i));
				break;
			}
		}
		st.println(out.toString());
	}

	public String getPostfix() {
		return postfix;
	}

	public String getPrefix() {
		return prefix;
	}

	public Map<String, String> getVarMap() {
		return varMap;
	}

	public void setVarMap(Map<String, String> varMap) {
		this.varMap = varMap;
	}

}// end of class

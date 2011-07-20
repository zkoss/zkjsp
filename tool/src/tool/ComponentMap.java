/* ComponentMap.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 21, 2008 4:21:28 PM 2007, Created by Flyworld
}}IS_NOTE

Copyright (C) 2008 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
 */
package tool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.zkoss.zk.ui.metainfo.ComponentDefinition;
import org.zkoss.zk.ui.metainfo.ComponentDefinitionMap;
import org.zkoss.zk.ui.metainfo.LanguageDefinition;

public class ComponentMap {
	Map<String, ArrayList> varMap;
	int _size = -1;

	public ComponentMap() {
		varMap = new HashMap<String, ArrayList>();
	}

	public void setLanguageDefinition(LanguageDefinition ld) {
		ComponentDefinitionMap map = ld.getComponentDefinitionMap();
		ArrayList al = new ArrayList<String>();
		al.addAll(map.getNames());
		ComponentMap cmpMap = new ComponentMap();
		_size = al.size();
		for (int i = 0; i < al.size(); i++) {
			ComponentDefinition cmpdefi = ld.getComponentDefinition(al.get(i)
					.toString());
			Class cls = (Class) cmpdefi.getImplementationClass();
			this.addValue(cls.getPackage().getName(), al.get(i).toString());
		}
	}

	private void addValue(String packageName, String className) {
		if (varMap.containsKey(packageName)) {
			ArrayList tempArrayList = varMap.get(packageName);
			varMap.remove(packageName);
			tempArrayList.add(className);
			varMap.put(packageName, tempArrayList);
		} else {
			ArrayList tempArrayList = new ArrayList();
			tempArrayList.add(className);
			varMap.put(packageName, tempArrayList);
		}
	}

	public int getMapSize() {
		return _size;
	}

	public Object[] getMapKeysObj() {
		return getMapKeys();
	}

	public Object[] getMapValuesObj() {
		return varMap.values().toArray();
	}

	/**
	 * get sorted map keys
	 * */
	public String[] getMapKeys() {
		String[] allKeys = (String[]) varMap.keySet().toArray(new String[] {});
		ArrayList alst = new ArrayList();
		for (String key : allKeys) {
			alst.add(key);
		}
		Collections.sort(alst);
		return (String[]) alst.toArray(new String[] {});
	}

	/**
	 * get sorted map values with sorted keys
	 * */
	public Object[][] getMapVales() {
		String[] allKeys = getMapKeys();
		List<String[]> lst = new LinkedList<String[]>();

		for (String key : allKeys) {
			ArrayList al = varMap.get(key);
			Collections.sort(al);
			String[] temp = (String[]) al.toArray(new String[] {});
			lst.add(temp);
		}
		return lst.toArray(new String[][] {});

	}
}

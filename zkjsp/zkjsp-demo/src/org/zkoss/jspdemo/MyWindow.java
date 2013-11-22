/* MyWindow.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Jul 26, 2007 10:25:44 AM 2007, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jspdemo;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.Annotation;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zul.Html;
import org.zkoss.zul.Window;

/**
 * A test Window used in zkjsp-demo
 * @author ian
 */
public class MyWindow extends Window implements AfterCompose{
	private static final long serialVersionUID = 1788318990308436631L;
	
	private String myValue;
	/**
	 * 
	 *test if onCreat event will be triggerd.
	 */
	public void onCreate()
	{
		System.out.println("MyWindow OnCreate Event happend.");
	}

	public void afterCompose() {
		System.out.println("MyWindow::afterCompose(): start print Annotation...");
		ByteArrayOutputStream ab = new ByteArrayOutputStream();
		PrintStream aPrintStream = new PrintStream(ab);
		recursivePrint(this, aPrintStream, 0);
		
		Html infoHtml = (Html) this.getFellowIfAny("info");
		if(infoHtml!=null)
			infoHtml.setContent("<pre>"+new String(ab.toByteArray())+"</pre>");
		
	}
	/**
	 * 
	 * @return
	 */
	public String getMyValue() {
		return myValue;
	}
	/**
	 * 
	 * @param myValue
	 */
	public void setMyValue(String myValue) {
		this.myValue = myValue;
	}
	/**
	 * 
	 * @param comp
	 * @param out
	 */
	private void recursivePrint(Component comp, PrintStream out, int level)
	{
		StringBuffer sb = new StringBuffer();
		for(int i=level;i>0;i--)out.print(" |");
		dump(sb,comp);
		out.println(sb);
		for(Iterator<Component> itor = comp.getChildren().iterator();itor.hasNext();)
			recursivePrint( itor.next(),out,level+1);
	}
	
	/**
	 * 
	 * @param sb
	 * @param comp
	 */
	@SuppressWarnings("deprecation")
	private static void dump(StringBuffer sb, Component comp) 
	{
		String srClss=comp.getClass().toString();
		srClss = srClss.substring(srClss.lastIndexOf(".")+1);
		ComponentCtrl compCtrl = (ComponentCtrl)comp;
		sb.append("->"+srClss+"-"+comp.getId()+"=")
			.append(compCtrl.getAnnotations().size()+" : ")
			.append(compCtrl.getAnnotatedProperties().size());
		String prop;
		for (Iterator<Annotation> it = compCtrl.getAnnotations().iterator(); it.hasNext();) {
			Annotation annot = it.next();
			sb.append(" self").append(annot);
		}
		for (Iterator<String> it = compCtrl.getAnnotatedProperties().iterator(); it.hasNext();) {
			prop = it.next();
			sb.append(" ").append(prop)
			.append(compCtrl.getAnnotations(prop));
		}
	}

	
}

/**
 * 
 */
package org.zkoss.jspdemo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

/**
 * @author ian
 *
 */
public class MyInit implements Initiator {
	
	private static final SimpleDateFormat yyyy_MM_dd_hh_mm_ss = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

	public void doInit(Page page, Map args) throws Exception {
		ArrayList list = new ArrayList();
		page.setVariable("current_date", new Date());
		for(int i=0;i<10;i++)
		{
			final String ref = "index: "+i;
			list.add(new MyValue(){
				public String getDate() {
					return yyyy_MM_dd_hh_mm_ss.format(new Date());
				}
				public String getValue() {
					return ref;
				}
			});
		}
		page.setVariable("my_list", list);
		for(Object obj : args.entrySet()){
			Entry entry = (Entry) obj;
			page.setVariable((String) entry.getKey(), entry.getValue());
		}
	}
	
	public void doAfterCompose(Page page) throws Exception {
		Object a = page.getVariable("current_date");
		System.out.println("MyInit::doAfterCompose(): current_date= "+a);
	}

	public boolean doCatch(Throwable ex) {
		return false;

	}

	public void doFinally() {

	}
	


}

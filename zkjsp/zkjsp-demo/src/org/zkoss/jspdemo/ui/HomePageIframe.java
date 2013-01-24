/**
 * 
 */
package org.zkoss.jspdemo.ui;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Toolbarbutton;


/**
 * @author Ian YT Tsai (zanyking)
 *
 */
public class HomePageIframe extends Iframe implements AfterCompose{
	private static final long serialVersionUID = 994426281285293866L;
	
	private static final String EQ_NAME = "ZKJSP_EQ_NAME";
	
	
	
	public static void toLink(HPLink hpLink){
		EventQueue<Event> eq = EventQueues.lookup(EQ_NAME);
		eq.publish(new Event("onUrlChange",null, hpLink));
	}



	@Override
	public void afterCompose() {
		EventQueue<Event> eq = EventQueues.lookup(EQ_NAME);
		eq.subscribe(new EventListener<Event>() {
			public void onEvent(Event evt) throws Exception {
				HPLink link = (HPLink) evt.getData();
				HomePageIframe.this.setSrc(link.getUrl());
				getTbtn().setLabel(link.getLabel());
				getTbtn().setHref(link.getUrl());
			}
		});
	}
	
	private Toolbarbutton getTbtn(){
		return (Toolbarbutton) this.getFellow("openNewBrowserBtn");
	}
	
}

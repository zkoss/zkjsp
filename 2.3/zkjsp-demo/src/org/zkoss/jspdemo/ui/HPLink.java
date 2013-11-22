/**
 * 
 */
package org.zkoss.jspdemo.ui;

import org.zkoss.jspdemo.HomePageCtrl;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Toolbarbutton;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
public class HPLink extends Toolbarbutton implements AfterCompose {
	private static final long serialVersionUID = -1790555298578215392L;
	
	private String url;
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void onClick(Event event){
		HomePageIframe.toLink(this);
		HomePageCtrl.highlight(this);
	}

	@Override
	public void afterCompose() {
		
	}
	
	
	
}

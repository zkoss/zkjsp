/**
 * 
 */
package org.zkoss.jspdemo.bug;

import java.util.Date;

import org.zkoss.bind.GlobalCommandEvent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.ui.select.annotation.Subscribe;
import org.zkoss.zul.Label;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
public class BugFix_11_Composer extends SelectorComposer<Component> {

	@Wire
	Label myLabel;
	
	@Subscribe("myqueue")
    public void updateValue1(Event evt){
        if(evt instanceof GlobalCommandEvent){
        	System.out.println("test!!!");
            if("updateValue1".equals(((GlobalCommandEvent)evt).getCommand())){
                //update shopping cart's items
            	myLabel.setValue("@Subscribe is successfully notified! selectorcomposer's doBeforCompose is healthy!");
            }               
        }
    }
	
}

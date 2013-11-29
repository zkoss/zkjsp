package org.zkoss.jspdemo;

import java.util.List;

import org.zkoss.jspdemo.ui.HPLink;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
/**
 * 
 * @author Ian YT Tsai (zanyking)
 *
 */
public class HomePageCtrl extends SelectorComposer<Component> {

	@Wire("hp-link")
	private List<HPLink> fHPLinks;
	
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		Executions.getCurrent().getDesktop().setAttribute("HomePageCtrl", this);
	}



	public static void highlight(HPLink selected){
		HomePageCtrl hpCtrl = 
			(HomePageCtrl) Executions.getCurrent().getDesktop().getAttribute("HomePageCtrl");
		
		for(HPLink hpLink : hpCtrl.fHPLinks){
			hpLink.setSclass("");
		}
		selected.setSclass("highlightedlink");
	}
	
	
	
}

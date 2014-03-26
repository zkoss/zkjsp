package org.zkoss.jspdemo.bug;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

public class FruitProvider extends SelectorComposer<Listbox> {

	List<String[]> fruits;
	public List<String[]> getFruits() {
		return fruits;
	}
	public void setFruits(List<String[]> fruits) {
		this.fruits = fruits;
	}
	public void doAfterCompose(Listbox comp) throws Exception {
		super.doAfterCompose(comp);
		fruits = new ArrayList<String[]>();
		fruits.add(new String[]{"Apple", "Apple1"});
		fruits.add(new String[]{"Banana", "Banana1"});
		fruits.add(new String[]{"Cherry", "Cherry1"});
		comp.setModel(new ListModelList(fruits));
	}
}
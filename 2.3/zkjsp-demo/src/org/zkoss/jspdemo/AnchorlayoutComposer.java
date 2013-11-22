package org.zkoss.jspdemo;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Anchorchildren;
import org.zkoss.zul.Anchorlayout;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class AnchorlayoutComposer extends GenericForwardComposer {
	Anchorlayout parentLayout;
	Anchorchildren ghostChild;
	int childPerLine = 5;
	int lineHeight = 30;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		addChildWindows(parentLayout, ghostChild);
		calcFirst(parentLayout, ghostChild, true);
	}

	// calculate the size of first child
	public void calcFirst(Anchorlayout parent, Anchorchildren ghost, boolean maximaze) {
		Anchorchildren first = (Anchorchildren)parent.getFirstChild();
		if (maximaze) {
			int extra = first.equals(ghost)? 1 : 2;
			int amount = parent.getChildren().size()-extra;
			int level = (amount)/childPerLine;
			if (amount % childPerLine > 0)
				level ++;
			int height = (0-lineHeight) * level;
			first.setAnchor("100% "+ height);
			first.invalidate();
		} else {
			first.setAnchor("0% 0%");
			first.invalidate();
		}
	}
	// maximize an anchor child
	public void maximize (Anchorlayout parent, Anchorchildren child, Anchorchildren ghost) {
		if ("30px".equals(child.getHeight())) {
			Anchorchildren first = (Anchorchildren)parent.getFirstChild();
			// ghost not first child, close first child first
			if (!first.equals(ghost))
				close(parent, first, ghost);

			calcFirst(parent, ghost, false);
			parent.insertBefore(ghost, child);
			parent.insertBefore(child, parent.getFirstChild());			
			child.setHeight("");
			calcFirst(parent, ghost, true);

			((Window)child.getFirstChild()).setMaximized(false);
			((Window)child.getFirstChild()).setMaximizable(false);
			child.invalidate();
		}
	}
	// close (minimize) an anchor child
	public void close(Anchorlayout parent, Anchorchildren child, Anchorchildren ghost) {
		if (!"30px".equals(child.getHeight())) {
			((Window)child.getFirstChild()).setMaximizable(true);
			parent.insertBefore(child, ghost);
			parent.insertBefore(ghost, parent.getFirstChild());
			child.setHeight("30px");
			child.setAnchor("20%");
			child.invalidate();
			calcFirst(parent, ghost, true);
		}
		child.getFirstChild().setVisible(true);
	}
	// delete an anchor child
	public void delete(Anchorlayout parent, Anchorchildren child, Anchorchildren ghost) {
		if (parent.getFirstChild().equals(child))
			close(parent, child, ghost);
		child.setParent(null);
		calcFirst(parent, ghost, true);
	}
	// drop an anchor child on another 
	public void doDrop(Event event, Anchorlayout parent, Anchorchildren ghost) {
		Anchorchildren first = (Anchorchildren)parent.getFirstChild(), dragged = (Anchorchildren)((DropEvent)event).getDragged(),
			target = (Anchorchildren)event.getTarget();
		if (dragged.equals(first) || target.equals(first)) {
			Anchorchildren swapTarget = dragged.equals(first)? target : dragged;
			swap(parent, ghost, swapTarget);
		} else {
			swap(parent, dragged, target);
		}
	}
	private void swap(Anchorlayout parent, Anchorchildren first, Anchorchildren second) {
		Anchorchildren tmp = new Anchorchildren();
		parent.insertBefore(tmp, first);
		parent.insertBefore(first, second);
		parent.insertBefore(second, tmp);
		tmp.setParent(null);
	}
	// add anchorchildren to anchorlayout
	public void addChildWindows(final Anchorlayout parent, final Anchorchildren ghost) {
		for (int i = 1;i != 13;i ++) {
			final Anchorchildren c = new Anchorchildren();
			Window w = new Window();
			initChild(c, parent, ghost);
			initWindow(w, i, parent, c, ghost);
			addContent(w);
			w.setParent(c);
			c.setParent(parent);
		}
	}
	private void initChild(Anchorchildren c, final Anchorlayout parent, final Anchorchildren ghost) {
		c.setAnchor("20%"); c.setHeight("30px"); c.setDraggable("true"); c.setDroppable("true");
		c.addEventListener("onDrop", new EventListener() {
			public void onEvent(Event event) {
				doDrop(event, parent, ghost);
			}
		});
	}
	private void initWindow(Window w, int i, final Anchorlayout parent, final Anchorchildren c, final Anchorchildren ghost) {
		w.setTitle("window "+i); w.setBorder("normal"); w.setHflex("1"); w.setVflex("1");
		w.setClosable(true); w.setMaximizable(true); w.setMinimizable(true);
		w.addEventListener("onMaximize", new EventListener() {
			public void onEvent(Event event) {
				maximize(parent, c, ghost);
		}});
		w.addEventListener("onMinimize", new EventListener() {
			public void onEvent(Event event) {
				close(parent, c, ghost);
		}});
		w.addEventListener("onClose", new EventListener() {
			public void onEvent(Event event) {
				delete(parent, c, ghost);
		}});
	}
	private void addContent(Window w) {
		Label l = new Label();
		l.setValue("Content of "+w.getTitle());
		l.setParent(w);
	}
}

package org.zkoss.jspdemo.listener;

import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.BookmarkEvent;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.util.Composer;

public class SEOComposer implements Composer {
	public void doAfterCompose(Component comp) throws Exception {
		// only handle once.
		// System.out.println(SEOComposer.class + " - do AfterCompose");
		Object seo = comp.getDesktop().getAttribute("SEO");
		if (seo == null) {
			String name = Executions.getCurrent().getParameter("bk");
			if (!Strings.isEmpty(name)) {
				Events.postEvent(new BookmarkEvent("onBookmarkChange", name));
				comp.getDesktop().setAttribute("SEO", Boolean.TRUE);
			}
		}
	}
}
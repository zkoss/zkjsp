package org.zkoss.jspdemo.listener;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;

public class SEORenderer implements org.zkoss.zk.ui.sys.SEORenderer {
	public void render(Page page, Writer out) throws IOException {
		// System.out.println(SEORenderer.class + " - Render...");
		for (Iterator it = SEODesktopInit.SEOMap.entrySet().iterator(); it
				.hasNext();) {
			Map.Entry me = (Map.Entry) it.next();
			String url = Executions.encodeURL("/" + me.getValue());
			if (url.indexOf("?bk=") >= 0) {
				int start = url.indexOf("?bk=");
				int end = url.indexOf("&", start + 4);
				url = url.substring(0, start) + "?bk=" + me.getKey()
						+ url.substring(end + 1, url.length());
			} else if (url.indexOf("&bk=") >= 0) {
				int start = url.indexOf("&bk=");
				int end = url.indexOf("&", start + 4);
				url = url.substring(0, start) + "&bk=" + me.getKey()
						+ url.substring(end + 1, url.length());
			} else {
				if (url.indexOf("?") >= 0) {
					url += "&bk=" + me.getKey();
				} else {
					url += "?bk=" + me.getKey();
				}
			}
			out.write("<a href=\"" + url + "\">" + me.getKey() + "</a>");
		}
	}
}
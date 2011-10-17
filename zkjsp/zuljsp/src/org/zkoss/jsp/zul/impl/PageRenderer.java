/* PageRenderer.java

	Purpose:
		
	Description:
		
	History:
		Wed Oct  7 17:08:21     2009, Created by tomyeh

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

This program is distributed under GPL Version 3.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/
package org.zkoss.jsp.zul.impl;

import java.util.Iterator;
import java.io.Writer;
import java.io.IOException;

import org.zkoss.xml.XMLs;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.sys.ComponentCtrl;
import org.zkoss.zk.ui.sys.HtmlPageRenders;
import org.zkoss.zk.ui.sys.PageCtrl;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.lang.Classes;
import org.zkoss.util.Utils;
import org.zkoss.zk.ui.sys.SEORenderer;


/**
 * The page renderer used for rendering this page.
 * @author tomyeh
 * @since 1.4.0
 */
public class PageRenderer implements org.zkoss.zk.ui.sys.PageRenderer {
	private final boolean _SEORenderReady, _SEOContentReady;
	public PageRenderer() {
		boolean seor, seoc;
		try {
			Classes.forNameByThread("org.zkoss.zk.ui.sys.SEORenderer");
			seor = true;
		} catch (Exception e) {
			seor = false;
		}
		try {
			Class[] param = {Page.class, Writer.class};
			HtmlPageRenders.class.getMethod("outSEOContent", param);
			seoc = true;
		} catch (Exception e) {
			seoc = false;
		}
		_SEORenderReady = seor;
		_SEOContentReady = seoc;
	}
	public void render(Page page, Writer out) throws IOException {
		final Execution exec = Executions.getCurrent();
		final Desktop desktop = exec.getDesktop();

		out.write(HtmlPageRenders.outHeaders(exec, page, true));
		out.write(HtmlPageRenders.outLangStyleSheets(exec, null, null));
		out.write(HtmlPageRenders.outLangJavaScripts(exec, null, null));
		out.write(HtmlPageRenders.outHeaders(exec, page, false));

		out.write("<script type=\"text/javascript\">zkpb('");
		out.write(page.getUuid());
		out.write("','");
		out.write(desktop.getId());
		out.write("','");
		out.write(getContextURI(exec));
		out.write("','");
		out.write(desktop.getUpdateURI(null));
		out.write("','");
		out.write(desktop.getRequestPath());
		out.write('\'');

		String style = page.getStyle();
		if (style != null && style.length() > 0) {
			out.write(",{style:'");
			out.write(style);
			out.write("'}");
		}

		out.write(");zkpe();</script>\n");

		for (Iterator it = page.getRoots().iterator(); it.hasNext();) {
			final Component comp = (Component)it.next();
			if (!(comp instanceof Inline)) {
				out.write("<div");
				writeAttr(out, "id", comp.getUuid());
				out.write(" class=\"z-temp\">");

				if (page != null) {
					// use HtmlPageRenders.outSEOContent if it is ready
					if (_SEOContentReady)
						 HtmlPageRenders.outSEOContent(page, out);
					// or use 
					else if (_SEORenderReady && ((PageCtrl)page).getOwner() == null) {
						final WebApp wapp = page.getDesktop().getWebApp();
						SEORenderer[] seos = wapp
							.getConfiguration().getSEORenderers();
						for (int i = 0;i < seos.length;i ++) {
							(seos[i]).render(page, out);
						}
					}
				}
				out.write("</div><script type=\"text/javascript\">");
			}

			((ComponentCtrl)comp).redraw(out);

			if (!(comp instanceof Inline)) {
				out.write("</script>\n");
			}
		}

		out.write(HtmlPageRenders.outResponseJavaScripts(exec));
	}
	private static String getContextURI(Execution exec) {
		if (exec != null) {
			String s = exec.encodeURL("/");
			int j = s.lastIndexOf('/'); //might have jsessionid=...
			return j >= 0 ? s.substring(0, j) + s.substring(j + 1): s;
		}
		return "";
	}
	private static final void writeAttr(Writer out, String name, String value)
	throws IOException {
		out.write(' ');
		out.write(name);
		out.write("=\"");
		out.write(XMLs.encodeAttribute(value));
		out.write('"');
	}
}

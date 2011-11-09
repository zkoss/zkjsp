package org.zkoss.jspdemo.listener;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.AuService;
import org.zkoss.zk.ui.Desktop;

public class SEODesktopInit implements org.zkoss.zk.ui.util.DesktopInit {
	public static Map SEOMap = new HashMap();

	public void init(final Desktop desktop, Object request) throws Exception {
		// System.out.println(SEODesktopInit.class + " - SEODesktopInit");
		desktop.addListener(new AuService() {

			public boolean service(AuRequest request, boolean everError) {
				final String cmd = request.getCommand();
				// System.out.println(SEODesktopInit.class +
				// " - do service, cmd= " + cmd);
				if (cmd.equals("onBookmarkSEO")) {
					String name = (String) request.getData().get("nm");
					String uri = (String) request.getData().get("uri");
					SEOMap.put(name, uri);
					return true;
				} else
					return false;
			}
		});
	}
}
package org.zkoss.jspdemo.listener;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.util.InitiatorExt;

public class SystemWideInitiator implements Initiator, InitiatorExt {

	@Override
	public void doAfterCompose(Page arg0, Component[] comps) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean doCatch(Throwable arg0) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(SystemWideInitiator.class+" - method - doCatch");
		return false;
	}

	@Override
	public void doFinally() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void doInit(Page arg0, Map<String, Object> arg1) throws Exception {
		// TODO Auto-generated method stub
		// System.out.println(" initiator set value");
		arg0.setAttribute("System-wide initiator test value", "inited value", Page.REQUEST_SCOPE);
	}

}

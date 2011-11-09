package org.zkoss.jspdemo;

import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.util.InitiatorExt;

public abstract class AbstractMyInit implements Initiator, InitiatorExt {
	public abstract void doInit(Page page, Map<String, Object> args) throws Exception;
	public abstract void doAfterCompose(Page page, Component[] comps) throws Exception;
	public abstract boolean doCatch(Throwable ex) throws Exception;
	public abstract void doFinally() throws Exception;
}

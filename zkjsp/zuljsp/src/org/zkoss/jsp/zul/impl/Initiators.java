/* Initiators.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Wed August 08 14:27:47     2007, Created by Ian Tsai
}}IS_NOTE

Copyright (C) 2007 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.jsp.zul.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.jsp.JspContext;

import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.util.Initiator;
import org.zkoss.zk.ui.util.InitiatorExt;

/**
 *  A helper class used to handle {@link Initiator} in jsp.
 * 
 * @author Ian Tsai
 *
 */
public class Initiators{
	static final Log log = Log.lookup(Initiators.class);
	private final List _inits;
	private Initiator[] _sysinits;
	/**
	 * 
	 */
	private Initiators()
	{
		 _inits = new LinkedList();
	}
	/**
	 *  get Initiators from JspContext
	 * @param context the context used to store Inits.
	 * @return Initiators
	 */
	public static Initiators getInstance(JspContext context){
		Initiators inits = (Initiators) context.getAttribute(Initiators.class.getName());
		if(inits==null)
			context.setAttribute(
				Initiators.class.getName(), inits = new Initiators());
		return inits;
	}

	/**
	 * Add new initiator and it's args into this handler. 
	 * @param init the initiator
	 * @param args the args
	 */
	public void addInitiator(Initiator init, LinkedHashMap args) {
		_inits.add(new Object[]{init,args});
	}
	
	/** Invokes {@link Initiator#doInit}, if any, and returns
	 * an instance of{@link Initiators}.
	 */
	public void doInit(Page page, Initiator[] sysinits) {
		if (sysinits == null) {
			sysinits = new Initiator[0];
		} else {
			try {
				for (int j = 0; j < sysinits.length; ++j)
					sysinits[j].doInit(page, Collections.EMPTY_MAP);
			} catch (Throwable ex) {
				throw UiException.Aide.wrap(ex);
			}
		}
		_sysinits = sysinits;
		Object[] objArr;
		Initiator initiator;
		LinkedHashMap args;
		for (Iterator it = _inits.iterator(); it.hasNext();) {
			objArr = (Object[]) it .next();
			initiator = (Initiator) objArr[0];
			args = (LinkedHashMap) objArr[1];
			try {
				initiator.doInit(page, args);
			} catch (Throwable ex) {
				throw UiException.Aide.wrap(ex);
			}
		}
	}
	
	/**
	 * 
	 * @param page
	 * @throws Exception
	 */
	public void doAfterCompose(Page page, List compTags) throws Exception {
		
		Component[] comps = null;
		if(compTags!=null){
			Object[] tags = compTags.toArray();
			comps = new Component[ tags.length];
			LeafTag tag;
			for(int i=tags.length-1;i>=0;i--){
				tag = (LeafTag)tags[i];
				comps[i] = tag.getComponent();
			}
		}
		for (int j = 0; j < _sysinits.length; ++j) {
			final Initiator init = _sysinits[j];
			if (init instanceof InitiatorExt) {
				if (comps == null) comps = new Component[0];
				((InitiatorExt)init).doAfterCompose(page, comps);
			} else {
				((InitiatorExt)init).doAfterCompose(page, comps);
			}
		}
		Initiator init;
		for (Iterator it = _inits.iterator(); it.hasNext();) {
			init = (Initiator)((Object[])it.next())[0];
			if (init instanceof InitiatorExt) {
				if (comps == null) comps = new Component[0];
				((InitiatorExt)init).doAfterCompose(page, comps);
			} else 
				((InitiatorExt)init).doAfterCompose(page, comps);
		}
	}
	/** Invokes {@link Initiator#doCatch}.
	 * It eats all exception without throwing one (but logging).
	 * Caller has to re-throw the exception.
	 */
	public void doCatch(Throwable t) {
		for (int j = 0; j < _sysinits.length; ++j) {
			final Initiator init = _sysinits[j];
			try {
				((InitiatorExt)init).doCatch(t);
			} catch (Throwable ex) {
				log.error(ex);
			}
		}
		for (Iterator it = _inits.iterator(); it.hasNext();) {
			final Initiator init = ((Initiator)((Object[])it.next())[0]);
			try {
				((InitiatorExt)init).doCatch(t);
			} catch (Throwable ex) {
				log.error(ex);
			}
		}
	}
	/** Invokes {@link Initiator#doFinally}.
	 */
	public void doFinally() {
		Throwable t = null;
		for (int j = 0; j < _sysinits.length; ++j) {
			final Initiator init = _sysinits[j];
			try {
				((InitiatorExt)init).doFinally();
			} catch (Throwable ex) {
				log.error(ex);
				if (t == null) t = ex;
			}
		}
		for (Iterator it = _inits.iterator(); it.hasNext();) {
			final Initiator init = ((Initiator)((Object[])it.next())[0]);
			try {
				((InitiatorExt)init).doFinally();
			} catch (Throwable ex) {
				log.error(ex);
				if (t == null) t = ex;
			}
		}
		if (t != null) throw UiException.Aide.wrap(t);
	}
	
}

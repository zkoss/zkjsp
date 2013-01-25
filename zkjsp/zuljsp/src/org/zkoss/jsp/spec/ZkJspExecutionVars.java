/**
 * 2013.01.25 
 */
package org.zkoss.jsp.spec;

import javax.servlet.jsp.JspContext;

import org.zkoss.jsp.zul.impl.ComponentJspContextStack;
import org.zkoss.jsp.zul.impl.Jsps;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.xel.impl.ExecutionResolver;

/**
 * 
 * A helper class designed for ZK JSP to do variable resolving in ZK Execution Context. 
 * 
 * @author Ian YT Tsai(zanyking)
 *
 */
public class ZkJspExecutionVars {
	
	/**
	 * 
	 * @param name the name to retrieve instance.
	 * @param jspContext 
	 * @return
	 */
	public static Object resolveVariable(String name, JspContext jspContext){
		final Execution exec = Executions.getCurrent();
		if(exec==null)return null;
		ExecutionResolver resolver = new ExecutionResolver(exec, null);
		Component comp = ComponentJspContextStack.peakCurrent(jspContext);
		
		resolver.setSelf(comp==null ? 
			Jsps.getZkPageObject(jspContext) : comp);
		
		return resolver.resolveVariable(name);		
	}
}

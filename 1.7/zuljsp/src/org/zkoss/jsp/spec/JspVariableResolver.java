/**
 * 
 */
package org.zkoss.jsp.spec;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.el.ELException;
import javax.servlet.jsp.el.VariableResolver;

import org.zkoss.jsp.zul.impl.ComponentJspContextStack;
import org.zkoss.jsp.zul.impl.Jsps;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.xel.impl.ExecutionResolver;

/**
 * @author Jimmy
 *
 * 2009
 */
public class JspVariableResolver implements VariableResolver{
	private final JspContext context;
	
	/**
	 * 
	 * @param context
	 */
	public JspVariableResolver(JspContext context) {
		this.context = context;
	}

	/*Implementation Logic(non-Javadoc)
	 * 1. get Variable from Jsp world
	 * 2. if null, get variable from ZK world
	 */
	public Object resolveVariable(String name) throws ELException {
		final Execution exec = Executions.getCurrent();
		if(exec==null)return null;
		ExecutionResolver resolver = new ExecutionResolver(exec, null);
		Component comp = ComponentJspContextStack.peakCurrent(context);
		if(comp==null)
			resolver.setSelf(Jsps.getZkPageObject(context));
		else resolver.setSelf(comp);
		return resolver.resolveVariable(name);		
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>helloMVVM Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<p>before zkjsp 2.3, doBeforeCompose is not been called, because back to the time we start ZKJSP there's no such zk facilities depends on it.
	but now, things are different, we got several stuffs need to do something at such stage and we have to check it out.</p>
	<p>The listed calls below are effected:</p>
	<ol>
		<li>SelectorComposer: Selectors.wireVariables(page, this, _resolvers);</li>
		<li>SelectorComposer: getUtilityHandler().subscribeEventQueues(this);</li>
	</ol> 
	
	
	<z:window apply="org.zkoss.bind.BindComposer"
		binder="@init(queueName='myqueue')"
		viewModel="@id('vm') @init('org.zkoss.jspdemo.bug.BugFix_11_Vm')">
		<z:textbox value="@bind(vm.textboxStr)" onChange="@command('doTextChange')"/>
		-> <z:label value="@load(vm.labelStr)" /> <br />
		@load(vm.textboxStr) = <z:label value="@load(vm.textboxStr)" /> <br />
		<z:button label="say hello" onClick="@command('sayHello')@global-command('updateValue1')">
			
		</z:button>
	</z:window>
	<z:window apply="org.zkoss.jspdemo.bug.BugFix_11_Composer" title="inside composer" border="normal" width="600px">
		
		<z:label id="myLabel"/> <br />
		
	</z:window>
</z:page>
</body>
</html>
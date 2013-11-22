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
	You should see 'text box' in a textbox and 'label' in a label at the begining <br />
	Click 'say hello' button <br />
	You should see 'Hello' in the textbox and 'MVVM' in the label
	<z:window apply="org.zkoss.bind.BindComposer"
		viewModel="@id('vm') @init('org.zkoss.jspdemo.bean.binding.HelloMVVM')">
		<z:textbox value="@load(vm.textboxStr)" />
		<z:label value="@load(vm.labelStr)" /> <br />
		<z:button label="say hello" onClick="@command('sayHello')" />
	</z:window>
</z:page>
</body>
</html>
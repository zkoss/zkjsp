<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System-wide initiator</title>
</head>
<body>


<z:page>
<z:zscript>
	String s = page.getAttribute("System-wide initiator test value", Page.REQUEST_SCOPE);
	System.out.println(s +" is null? "+(s==null));
</z:zscript>
<z:window id="mainPanel" width="600px" height="300px" title="SEORenderer with System-wide composer"  border="normal" >
	<z:caption id="mainCap" label="click button to change bookmark">
		
	</z:caption>
	System-wide initiator test,
	you should see "inited value" in textbox below
	<br />
	<!-- references:
			SystemWideInitiator.java
			private static Proxy newProxy5() in ZkProxy.java
			listener org.zkoss.listener.SystemWideInitiator in zk.xml
			
			http://books.zkoss.org/wiki/ZK%20Configuration%20Reference/zk.xml/The%20listener%20Element/The%20org.zkoss.zk.ui.util.Initiator%20interface
	 -->
	<br />
	
	<z:textbox id="initedTb" />
	<z:zscript>
		initedTb.setValue(s);
	</z:zscript>

</z:window>
</z:page>
</body>
</html>
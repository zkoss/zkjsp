<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<z:component name="username" inline="true" macroURI="/test/username.zul"/>
 <z:component name="mywin" class="org.zkoss.jspdemo.MyWindow" extends="window"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>static function call in JSP ...</title>
</head>
<body>
	<z:page id="includee">
		<z:zscript>
		Messagebox.show("test");
		</z:zscript>
		<z:label value="test" />
	</z:page>
</body>
</html>
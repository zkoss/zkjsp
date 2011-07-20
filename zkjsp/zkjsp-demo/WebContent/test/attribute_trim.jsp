<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>include a jsp ...</title>
</head>
<%
request.setAttribute("bb", new Boolean(true));
%>
<body>
<h1>Test attribute...</h1>
	<z:page>
		<z:window id="testWin" use="org.zkoss.jspdemo.MyWindow">
			<z:attribute name="title">      werthy   wretr ertr      </z:attribute>
			<z:attribute name="myValue" trim="true">      werthy   wretr ertr      </z:attribute>
			<z:attribute name="onCreate">
				System.out.println("["+self.title+"]");
				System.out.println("["+self.myValue+"]");
			</z:attribute>
			<z:label value="[${testWin.title}]"/>
			<z:label value="[${testWin.myValue}]"/>
		</z:window>
	</z:page>
</body>
</html>
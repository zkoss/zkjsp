<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="org.zkoss.jspdemo.model.*" %>
<%@ page import="org.zkoss.jspdemo.bean.*" %>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	session.setAttribute("session",session );
%>
<z:variable-resolver use="org.zkoss.jspdemo.JspScopeVariableResolver" arg0="${session}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Test Variable Resolver with Constructor Parameters</title>
</head>
<body>
<% 
	RestaurantRepository repository = new RestaurantRepository(session);
	session.setAttribute("defaultRes",repository.getAll().get(0) );
%>
<h1>Test Variable Resolver with Constructor Parameters</h1>
<z:page id="eltests">
	<z:zscript>
		String zStr =  defaultRes.name+":  lat="+defaultRes.location.latitude + ",  lng=" + defaultRes.location.longitude;
	</z:zscript>
	<z:label value="${zStr}"/>
	
</z:page>
</body>
</html>
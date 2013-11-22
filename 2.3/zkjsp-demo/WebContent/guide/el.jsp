<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="org.zkoss.jspdemo.model.*" %>
<%@ page import="org.zkoss.jspdemo.bean.*" %>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="zk" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:init use="org.zkoss.zkplus.databind.AnnotateDataBinderInit"/>

<%--<z:variable-resolver use="org.XXX.XXX.SpringVariableResolver"/>
<z:variable-resolver use="org.XXX.XXX.EjbVariableResolver"/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK JSP Expression Language Demo</title>
</head>
<body>
<%
    String myStr = "This is Scriptlet string.";
    request.setAttribute("myStr",myStr);
%>
<zk:page id="eltests">
    <zk:label value="${myStr}"/><%-- OK! --%>  
    <zk:button label="push me!">
        <zk:attribute name="onClick">
            alert("str is:"+requestScope.get("myStr"));// will cause failure!
        </zk:attribute>
    </zk:button>
</zk:page>

</body>
</html>
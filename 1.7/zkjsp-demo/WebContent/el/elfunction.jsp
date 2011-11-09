<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="org.zkoss.jspdemo.model.*" %>
<%@ page import="org.zkoss.jspdemo.bean.*" %>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="zf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Use ZK JSP Expression Language Test</title>
</head>
<body>
<h1>test gmap</h1>
<z:page id="eltests">
	<p>${zf:encodeURL("el.jsp")}</p>
	<z:zscript>
         String A = "This is page scope str A";
    </z:zscript>
    <z:window id="myWin">
        <z:zscript>
             String A = "This is myWin's A";
        </z:zscript>
        <z:label value="${A}"/>
    </z:window>
    <z:label value="${A}"/>
</z:page>

</body>
</html>
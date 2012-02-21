<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A simple twin page Demo</title>
</head>
<body>
<h2>ZK Jsp twin page Demo(out side the ZK Page)</h2>
<z:page zscriptLanguage="java" style="width:500px;height:100px"><%-- this is Jsp valid Comment, do not use XML comment. --%>
		<h2>First Page</h2>
		<z:window id="win1" title="ZK is best1!!!" border="normal" width="780px">
			test test
		</z:window>
</z:page>
<z:page zscriptLanguage="java" style="width:500px;height:100px"><%-- this is Jsp valid Comment, do not use XML comment. --%>
		<h2>second Page</h2>
		<z:window id="win1" title="ZK is best2!!!" border="normal" width="780px">
			test2 test2
		</z:window>
</z:page>
<h1>Should be shown</h1>

</body>
</html>
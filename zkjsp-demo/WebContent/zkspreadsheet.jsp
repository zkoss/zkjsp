<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK Spreadsheet</title>
</head>
<body>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
	<h2>ZK Spreadsheet</h2>
	<z:window height="100%">
		<z:spreadsheet id="fluSpreadsheet" src="/xls/swineFlu.xls" width="100%" height="100%" maxrows="51" maxcolumns="4" style="border:1px solid black;"/>
	</z:window>
</z:page>
</body>
</html>
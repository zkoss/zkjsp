<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<z:page>
    <z:div id="div"/>
    <z:zscript>
     	Executions.createComponents("grid.zul", div,null);
    </z:zscript>
</z:page>
</body>
</html>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
<z:page>
<h2>You shall see a grid bellow.</h2>
    <z:div id="div"/>
    <z:zscript>
     	Executions.createComponents("grid.zul", div,null);
    </z:zscript>
</z:page>
</body>
</html>
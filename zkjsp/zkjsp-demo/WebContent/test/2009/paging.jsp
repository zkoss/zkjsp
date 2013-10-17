<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test Paging ...</title>
</head>
<body>
<h1>Test attribute...</h1>

<z:page>
	<z:grid id="mygrid" mold="paging" pageSize="4" width="100%"> 
		<z:columns id="mycolumns" style="position:relative;"> 
			<z:column>No.</z:column> 
			<z:column>Name</z:column> 
		</z:columns> 
		<z:rows id="myrows" style="position:relative;">
			<z:row>0<z:label value="test0"/></z:row> 
			<z:row>1<z:label value="test1"/></z:row>
			<z:row>2<z:label value="test0"/></z:row> 
			<z:row>3<z:label value="test1"/></z:row>
			<z:row>4<z:label value="test0"/></z:row> 
			<z:row>5<z:label value="test1"/></z:row>
			<z:row>6<z:label value="test0"/></z:row> 
			<z:row>7<z:label value="test1"/></z:row>
			<z:row>8<z:label value="test0"/></z:row> 
			<z:row>9<z:label value="test1"/></z:row>
			<z:row>10<z:label value="test0"/></z:row> 
			<z:row>11<z:label value="test1"/></z:row>
		</z:rows> 
	</z:grid>
</z:page>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A hlayout and vlayout Demo</title>
</head>
<body>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
	<h2>hlayout and vlayout</h2>
	<z:window height="100%">
		<z:vlayout hflex="true" vflex="1">
			<z:div vflex="min" style="background:red">Top</z:div>
			<z:hlayout vflex="1">
					<z:div hflex="min" vflex="1" style="background:green">Left</z:div>
					<z:div hflex="1" vflex="1">
					You should see the size of each region is accroding to its content(just like borderlayout),
					even if you resize the browser window.
					</z:div>
					<z:div hflex="min" vflex="1" style="background:yellow">
						Right
					</z:div>
			</z:hlayout>
			<z:div vflex="min" style="background:cyan">Bottom</z:div>
		</z:vlayout>
	</z:window>
</z:page>
</body>
</html>
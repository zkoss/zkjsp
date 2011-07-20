<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<z:zkhead />
</head>
<body>
<z:page>
Test
	<z:tabbox>
		<z:tabs>
			<z:tab label="test" />
		</z:tabs>
		<z:tabpanels>
			<z:tabpanel>test</z:tabpanel>
		</z:tabpanels>
	</z:tabbox>
	<z:include src="head_auxheader.jsp" />	
</z:page>
<%@include file="head_capcha.jsp"%>
</body>
</html>
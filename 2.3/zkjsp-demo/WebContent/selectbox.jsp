<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Selectbox</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<z:zscript>
		String[] userName = { "Tony", "Ryan", "Jumper", "Wing", "Sam" };
		ListModelList model = new ListModelList(userName);
	</z:zscript>
	<z:selectbox model="${model}" onSelect='alert(model.get(event.getData()));' />
</z:page>
</body>
</html>
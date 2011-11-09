<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Colorbox Demo</title>
</head>
<body>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
	<h2>Colorbox</h2>
	<z:captcha id="cpa" length="6" width="150px"
						height="50px" />
	<z:colorbox color="#FFFFFF" onChange="cpa.setBgColor(self.color); cpa.randomValue();" />
</z:page>
</body>
</html>
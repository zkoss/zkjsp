<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<z:zkhead />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Biglistbox Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<z:zscript>
		import org.zkoss.jspdemo.model.*;
		import org.zkoss.util.*;
		
		FakerMatrixModel NonHeader = new FakerMatrixModel(1000000, 1000000);
	</z:zscript>
	<z:biglistbox id="biglist" hflex="1" vflex="1" colWidth="130px" model="${NonHeader}">
		
	</z:biglistbox>
</z:page>
</body>
</html>
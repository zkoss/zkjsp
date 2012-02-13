<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK JSP Tag Library Demo</title>
</head>
<body>
<h1>ZK JSP Tag Library Demo</h1>
<z:page zscriptLanguage="java">
	<%-- this is JSP valid Comment, do not use XML comment. --%>	
	<h3>Other Demos:</h3>
	<z:window id="win" title="ZK JSP demo" width="550px" border="normal">
		<z:vbox>
		Here is the list of ZK JSP Tag Library demos. 
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/binding/helloMVVM.jsp" label="hello MVVM" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
						href="./index2.jsp" label="back" />
			</z:hbox></z:div>
		</z:vbox>
	</z:window>
</z:page>
</body>
</html>
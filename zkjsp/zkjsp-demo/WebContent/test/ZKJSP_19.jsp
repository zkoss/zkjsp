<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK JSP Tag Library Demo</title>
<z:zkhead />
</head>
<body>
You should be able to see "Notify Party Details" and the "Test Button".
	<z:page>
		<z:tabbox width="100%" height="800px">
			<z:tabs>
				<z:tab label="Demo Cases" />
				<z:tab label="Test Cases" />
			</z:tabs>
			<z:tabpanels>
				<z:tabpanel style="overflow:auto">
					<z:label>Notify Party 
<c:out value="Details"/></z:label>
					<z:button>Test Button</z:button>
				</z:tabpanel>
			</z:tabpanels>
		</z:tabbox>
	</z:page>
</body>
</html>
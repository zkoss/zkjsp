<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Absolutelayout Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<z:absolutelayout width="800px" height="600px" style="background-color: #EEEEEE;">
		<z:absolutechildren x="100" y="100">
			<z:label value="child at (100, 100)" />
			<z:absolutelayout width="300px" height="300px" style="background-color: #D8D8D8;">
				<z:absolutechildren x="100" y="200">
					<z:label value="child at inner (100, 200), outer (200, 300)" />
				</z:absolutechildren>
			</z:absolutelayout>
		</z:absolutechildren>
	</z:absolutelayout>
</z:page>
</body>
</html>
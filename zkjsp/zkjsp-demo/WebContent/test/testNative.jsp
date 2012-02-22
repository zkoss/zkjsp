<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>native html demo</title>
	</head>
	<body>
		<z:page zscriptLanguage="java">
			<z:div width="300px">
				<!-- test html in jsp directly -->
				<table width="100%" border="1">
					<tr>
						<td>
							<z:label value="Text in html in jsp file"></z:label>
						</td>
					</tr>
				</table>
			</z:div>
			<z:div width="300px" />
			<z:include src="testNative-inc.zul" />
		</z:page>
	</body>
</html>
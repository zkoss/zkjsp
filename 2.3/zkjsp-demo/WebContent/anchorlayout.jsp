<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Anchorlayout Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<z:window border="normal" height="600px" sizable="true" apply="org.zkoss.jspdemo.AnchorlayoutComposer">
		<z:anchorlayout id="parentLayout" width="100%" height="100%">
			<z:anchorchildren id="ghostChild">
				<z:div width="100%" height="100%">
					you can maximize, minimize or close a window.<br />
					drag one window and drop it on another one will switch their position while minimize.
				</z:div>
			</z:anchorchildren>
		</z:anchorlayout>
	</z:window>
</z:page>
</body>
</html>
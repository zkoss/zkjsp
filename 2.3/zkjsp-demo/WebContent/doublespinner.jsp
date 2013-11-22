<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A Doublespinner Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	<%-- this is Jsp valid Comment, do not use XML comment. --%>
	<h2>Doublespinner</h2>
	1. Test each button's function works well.
    <z:separator />
	<z:doublespinner id="a1" mold="rounded" buttonVisible="false"
		constraint="no zero" />
	<z:doublespinner id="a2" buttonVisible="false" constraint="no zero" />
	<z:separator />
	<z:button label="button show">
		<z:attribute name="onClick">
			a1.setButtonVisible(!a1.isButtonVisible());
			a2.setButtonVisible(!a2.isButtonVisible());
		</z:attribute>
	</z:button>
	<z:button label="read only">
		<z:attribute name="onClick">
			a1.setReadonly(!a1.isReadonly());
			a2.setReadonly(!a2.isReadonly());
		</z:attribute>
	</z:button>
	<z:button label="Disable">
		<z:attribute name="onClick">
			a1.setDisabled(!a1.isDisabled());
			a2.setDisabled(!a2.isDisabled());
		</z:attribute>
	</z:button>
	<z:button label="Inplace">
		<z:attribute name="onClick">
			a1.setInplace(!a1.isInplace());
			a2.setInplace(!a2.isInplace());
		</z:attribute>
	</z:button>
	<z:separator />
	2. Test increasing/decreasing(each step is 0.5) the number of the double spinner. (min: -2.5, max: 2.5) 
	<z:doublespinner step="0.5" value="1" constraint="min -2.5 max 2.5" />
</z:page>
</body>
</html>
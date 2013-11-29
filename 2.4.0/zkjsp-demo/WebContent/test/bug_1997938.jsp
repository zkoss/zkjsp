<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>[ 1997938 ] ZscriptTag may cause NullPointerException</title>
</head>
<body>
<h1>[ 1997938 ] ZscriptTag may cause NullPointerException</h1>
<p>if everything works fine, you show see this message, and the content of Macro Window will be:

</p>
<ol>
	<li>Zscript var1</li>
	<li>this var is in zstest.zs!!!</li>
</ol>

	<z:page id="includee">
		<z:zscript/>
		<z:zscript src="/test/zstest.zs"/>
		<z:zscript>
			String testStr = "Zscript var1";
		</z:zscript>
		<z:window title="Macro Window" border="normal">
			<z:vbox>
				<z:label value="${testStr}"/>
			    <z:label value="${zsFileVar}"/>
			</z:vbox>
		</z:window>
	</z:page>
</body>
</html>

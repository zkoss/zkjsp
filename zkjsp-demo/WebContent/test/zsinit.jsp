<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<z:init zscript="/test/Person.zs"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>include a jsp ...</title>
</head>
<body>
	<z:page id="includee">
		<z:zscript>
			Person person = new Person();
			System.out.println("fire when load page..."+person );
		</z:zscript>
		<z:window title="Macro Window">
			<z:label value="change name"/>
		</z:window>
		<z:button label="change title" 
			onClick='self.setLabel("person is:"+person.firstName+" "+person.lastName)'/>
	</z:page>
</body>
</html>

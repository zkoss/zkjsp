<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test URL get resource</title>
</head>
<body>
<z:page>
<z:button label="Add Property">
  <z:attribute name="onClick">
    Window win = (Window) Executions.createComponents("/test/path.zul", null, null);
    win.doModal();
  </z:attribute>
</z:button>
</z:page> 
</body>
</html>
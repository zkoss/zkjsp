<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<z:component name="mywindow" extends="window" class="org.zkoss.jspdemo.MyWindow" title="test" border="normal"/>
<z:component name="username" inline="true" macroURI="/test/macro.zul" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dynamic Component Test</title>
</head>
<body>
	<h1>In page Dynamic Component usage</h1>
	<p>You can define a zk component in a JSP page,  for example:</p>
	<ol>
		<li>&lt;z:component name=&quot;mywindow&quot; extends=&quot;window&quot; class=&quot;org.zkoss.jspdemo.MyWindow&quot; title=&quot;test&quot; border=&quot;normal&quot;/&gt;</li>
		<li>&lt;z:component name=&quot;username&quot; inline=&quot;true&quot; macroURI=&quot;/test/macro.zul&quot; /&gt;</li>
	</ol>
	<p>Then, you can use it in your jsp page like this:<br/>
	&lt;z:ui tag=&quot;mywindow&quot; &gt;<br/>	&lt;z:ui tag=&quot;username&quot; /&gt;<br/>&lt;/z:ui&gt;<br/>
	</p>
	
	
	
	<z:page id="includee">
	<z:ui tag="mywindow" >
		<z:window id="main">
		    <z:button label="Add Item">    
		        <z:attribute name="onClick">        
		    new Label("Added at "+new Date()).setParent(main);    
		    new Separator().setParent(main);    
		        </z:attribute>    
		        <z:attribute name="onCreate">        
		    new Label("Added at "+new Date()).setParent(main);    
		    new Separator().setParent(main);    
		        </z:attribute>          
		    </z:button>    
		        
		    <z:ui tag="username" label="this is label!" text="this is text!"/>
		    
		</z:window>
	</z:ui>
	</z:page>

</body>
</html>

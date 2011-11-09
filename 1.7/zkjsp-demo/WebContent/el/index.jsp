<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="org.zkoss.jspdemo.model.*" %>
<%@ page import="org.zkoss.jspdemo.bean.*" %>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:init use="org.zkoss.zkplus.databind.AnnotateDataBinderInit"/>

<%--<z:variable-resolver use="org.zkoss.zkplus.spring.DelegatingVariableResolver"/>
<z:variable-resolver use="org.XXX.XXX.EjbVariableResolver"/>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Use ZK JSP Expression Language Test</title>
</head>
<body>
<h1>test gmap</h1>
<% 
	RestaurantRepository repository = new RestaurantRepository(session);
	session.setAttribute("repository", repository);
%>
<z:page id="eltests">
	<z:zscript>
		
		String title = "test title";
		int listBoxSize = 10;
		ArrayList zsList = new ArrayList();
		for(int i=0;i<3;i++)
			zsList.add("item_"+i);
	</z:zscript>
	<z:button label="test request variable resolver">
		<z:attribute name="onClick">
			self.label = ""+sessionScope.get("repository");
		</z:attribute>
	</z:button>
	<c:forEach var="zsStr" items="${zsList}">
			<h4>${zsStr}</h4><br/>
	</c:forEach>
	<z:window id="testWin" title="Expression Language Test, this is testWin" border="normal">
		<z:zscript>
			String innerStr = "this is inner String, can only be shown in window";
			
		</z:zscript>
		<z:listbox width="1000px">
			<z:listhead sizable="true">
				<z:listheader label="name" sort="auto"/>
				<z:listheader label="description" sort="auto"/>
				<z:listheader label="address" sort="auto"/>
				<z:listheader label="latitude" sort="auto"/>
				<z:listheader label="longitude" sort="auto"/>
				<z:listheader label="alert" sort="auto"/>
			</z:listhead>
		<c:forEach var="restaurant" items="${repository.all}">
			<z:listitem>
				<z:listcell label="${restaurant.name}"/>
				<z:listcell label="${restaurant.description}"/>
				<z:listcell label="${restaurant.location.address}"/>
				<z:listcell label="${restaurant.location.latitude}"/>
				<z:listcell label="${restaurant.location.longitude}"/>
				<z:listcell>
					<z:button label="Show Name">
						<z:custom-attributes restaurant="${restaurant}"/>
						<z:attribute name="onClick">
							self.label = self.getAttribute("restaurant").name;
						</z:attribute>
					</z:button>
				</z:listcell>
			</z:listitem>
		</c:forEach>
		</z:listbox>
			
		<z:label value="${title}"/>
		
		<c:forEach var="zsStr" items="${zsList}">
			<h4>${zsStr}</h4><br/>
		</c:forEach>
		<p>show innerStr: ${innerStr}</p>
		<z:window id="myWin" use="org.zkoss.jspdemo.MyWindow" myValue="myWin's myValue">
			<p>show innerStr: <span style="color:red">${innerStr}</span></p>
			use window id to get: <z:label style="color:green" value="${self.myValue}"/>
		</z:window>
		<p>show Custom class Window.myValue: <span style="color:red">${myWin.myValue}</span></p>
		<p> try to get info from "testWin"<br/> 
			id: <span style="color:red">${testWin.id}</span><br/> 
			title: <span style="color:blue">${testWin.title}</span></p>
			
		ZK Component attribute EL Test(inside testWin): <z:label style="color:green" value="${testWin.title}"/>
		ZK Component attribute EL Test(outSide myWin): <z:label style="color:green" value="${myWin.myValue}"/>
	</z:window>
	
	<span style="color:red"></span>
	<p>Out side testWin show title: <span style="color:red">${testWin.title}</span></p>
	<p>show innerStr(should be nothing): <span style="color:red">${innerStr}</span></p>
	
	<z:zscript>
         String A = "This is page scope str A";
    </z:zscript>
    <z:window id="myWin">
        <z:zscript>
             String A = "This is myWin's A";
        </z:zscript>
        <z:label value="${A}"/>
    </z:window>
    <z:label value="${A}"/>
</z:page>

</body>
</html>
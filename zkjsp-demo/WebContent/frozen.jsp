<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<z:component name="intbox" extends="intbox" constraint="no empty" style="text-align:right; color:blue" inplace="true" width="70px"/>
<z:component name="combobox" extends="combobox" constraint="no empty" style="text-align:right; color:blue" inplace="true" width="70px"/>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A Frozen Demo</title>
</head>
<body>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
		<h2>Frozen</h2>
		
		<p>The ZK grid component is enriched with spreadsheet functionalities such as frozen columns, in place editing, and merging the cells in grid.</p>
		
	<z:style src="/widgets/grid/spreadsheet_functionalities/css/demo.css.dsp"/>
	    <z:zscript>
		    String[] days = new String[31];
		    String[] years = { "2009", "2010" };
		    java.util.Random random = new java.util.Random();
		    java.util.Calendar cal = java.util.Calendar.getInstance();
		    void setValue(org.zkoss.zul.impl.InputElement ibox) {
		        int index = ibox.getAttribute("index");
		        cal.set(java.util.Calendar.MONTH, (ibox.getParent().getParent()
		                .getChildren().indexOf(ibox.getParent()) % 12));
		        if (cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH) > index) {
		            int value = random.nextInt(65536);
		            if (value % 17 == 0) {
		                ibox.setValue(-value + (ibox instanceof Intbox ? 0 : ""));
		                ibox.setStyle("text-align: right;color: red");
		            } else {
		                ibox.setValue(value + (ibox instanceof Intbox ? 0 : ""));
		            }
		        } else {
		            ibox.setDisabled(true);
		        }
		    }
	    </z:zscript>
	    <z:grid height="350px" width="817px" >
	        <z:frozen style="background: #dfded8" columns="3">
	            <z:div style="padding: 0 10px;" />
	        </z:frozen>
	        <z:columns>
	            <z:column sclass="header" label="Year" width="80px" />
	            <z:column sclass="header" label="Season" width="80px" />
	            <z:column sclass="header last" label="Month" width="80px" />
	            <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
					<z:column style="text-align:right;" 
	                	width="80px" label="${i}" />
	             </c:forEach>
	        </z:columns>
	        <z:rows>
	                <z:row>
	                    <z:cell sclass="years" rowspan="12">
	                        <z:label value="2010" />
	                    </z:cell>
	                    <z:cell sclass="season" rowspan="3">Q1</z:cell>
	                    <z:cell sclass="month last">Jan</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Feb</z:cell>
	                     <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:combobox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                        <z:comboitem label="111111"/>
	                        <z:comboitem label="222222"/>
	                        <z:comboitem label="333333"/>
	                        <z:comboitem label="444444"/>
	                        <z:comboitem label="555555"/>
	                    </z:combobox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Mar</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="season" rowspan="3">Q2</z:cell>
	                    <z:cell sclass="month last">Apr</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">May</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Jun</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="season" rowspan="3">Q3</z:cell>
	                    <z:cell sclass="month last">Jul</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Aug</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Sep</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="season" rowspan="3">Q4</z:cell>
	                    <z:cell sclass="month last">Qct</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Nov</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	                <z:row>
	                    <z:cell sclass="month last">Dec</z:cell>
	                    <c:forEach var="i" begin="1" end="31" step="1" varStatus="status">
	                    <z:intbox onChange='tip.setStyle("display:block");timer.start();'
	                        onCreate="setValue(self)">
	                        <z:custom-attributes
	                            index="${i}" />
	                    </z:intbox>
	                    </c:forEach>
	                </z:row>
	        </z:rows>
	        </z:grid>
	    <z:timer id="timer" delay="2000" running="false" repeats="true">
	        <z:attribute name="onTimer">
	            tip.setStyle("display:none");
	            timer.stop();
	        </z:attribute>
	    </z:timer>
	        <z:div id="tip" class="tooltip">
	            Click here to save the content!
	            <z:div class="tooltip-anchor">
	                <div class="tooltip-anchor-inner"></div>
	            </z:div>
	        </z:div>
</z:page>
</body>
</html>
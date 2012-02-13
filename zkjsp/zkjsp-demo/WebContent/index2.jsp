<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK JSP Tag Library Demo</title>
</head>
<body>
<h1>ZK JSP Tag Library Demo</h1>
<z:page zscriptLanguage="java">
	<%-- this is JSP valid Comment, do not use XML comment. --%>	
	<h3>Other Demos:</h3>
	<z:window id="win" title="ZK JSP demo" width="550px" border="normal">
		<z:vbox>
		Here is the list of ZK JSP Tag Library demos. 
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/gmap/index.jsp" label="gmap" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/2009/paging.jsp" label="paging.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/attribute_trim.jsp" label="attribute_trim.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/bug_1997938.jsp" label="bug_1997938.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/compose.jsp" label="compose.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/composer.jsp" label="composer.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/customattrs.jsp" label="customattrs.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/customize.jsp" label="customize.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/dynamic_comp.jsp" label="dynamic_comp.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/forward.jsp" label="forward.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/include.jsp" label="include.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/include3.jsp" label="include3.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/macro.jsp" label="macro.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/macrotest.jsp" label="macrotest.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/pathtest.jsp" label="pathtest.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/staticcall.jsp" label="staticcall.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/twinpage.jsp" label="twinpage.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/urlpath.jsp" label="urlpath.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/variableresolver.jsp" label="variableresolver.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/window.jsp" label="window.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/zsinit.jsp" label="zsinit.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/seo_systemwidecomposer.jsp" label="seo_systemwidecomposer.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
					href="./test/systemwideinitiator.jsp" label="systemwideinitiator.jsp" />
			</z:hbox></z:div>
			<z:div style="margin-top: 10px"><z:hbox>
					<z:toolbarbutton
						href="./index.jsp" label="back" />
					<z:toolbarbutton
						href="./index3.jsp" label="next" />
			</z:hbox></z:div>
		</z:vbox>
	</z:window>
</z:page>
</body>
</html>
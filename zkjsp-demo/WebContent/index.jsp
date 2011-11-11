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
	<h3>ZK Upload Demo:</h3>
	<z:window id="win3" title="Fileupload Demo" border="normal"
		width="550px">
		<p>Same as zkdemo userguide's Upload demo. press upload key to
		upload an image.</p>
		<z:button label="Upload">
			<z:attribute name="onClick">{
						Object media = Fileupload.get();
						if (media instanceof org.zkoss.image.Image) {
							Image image = new Image();
							image.setContent(media);
							image.setParent(pics);
						} else if (media != null)
							Messagebox.show("Not an image: "+media, "Error", Messagebox.OK, Messagebox.ERROR);
					}</z:attribute>
		</z:button>
		<z:vbox id="pics" />
	</z:window>
	<h3>Other Demos:</h3>
	<z:window id="win" title="ZK JSP demo" width="550px" border="normal">
		<z:vbox>
		Here is the list of ZK JSP Tag Library demos. 
			<div style="margin-top: 10px"><z:hbox>
					JSTL forEach works with ZK: <z:toolbarbutton href="./foreach.jsp"
					label="forEach Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					Use Annotation data binding : <z:toolbarbutton
					href="./databinding.jsp" label="data binding Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					Use Annotation data binding demo 2: <z:toolbarbutton
					href="./databinding2.jsp" label="data binding Demo 2" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					Use class to define customized ZK Component: <z:toolbarbutton
					href="./useclass.jsp" label="use class Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Drag and Drop features: <z:toolbarbutton
					href="./draganddrop.jsp" label="Drag and Drop Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					Combobutton Demo: <z:toolbarbutton
					href="./combobutton.jsp" label="Combobutton Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Capcha Component in jsp: <z:toolbarbutton href="./capcha.jsp"
					label="Capcha Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Window Component Mode setting in zscript: <z:toolbarbutton
					href="./windowmode.jsp" label="Window Mode Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Borderlayout component set from ZKEX: <z:toolbarbutton
					href="./borderlayout.jsp" label="Borderlayout Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					Absolutelayout Demo: <z:toolbarbutton
					href="./absolutelayout.jsp" label="Absolutelayout Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					Anchorlayout Demo: <z:toolbarbutton
					href="./anchorlayout.jsp" label="Anchorlayout Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Auxheader Demo from ZKEX: <z:toolbarbutton
					href="./auxheader.jsp" label="Auxheader Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK head Demo: <z:toolbarbutton
					href="./head.jsp" label="Head Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Flashchar Demo: <z:toolbarbutton
					href="./flashchar.jsp" label="Flashchar Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Frozen Demo: <z:toolbarbutton
					href="./frozen.jsp" label="Frozen Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK hlayout and vlayout Demo: <z:toolbarbutton
					href="./hlayoutAndVlayout.jsp" label="hlayout and vlayout Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK hlayout and vlayout Demo: <z:toolbarbutton
					href="./doublespinner.jsp" label="Doublespinner Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Colorbox Demo: <z:toolbarbutton
					href="./colorbox.jsp" label="Colorbox Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Calendar Demo: <z:toolbarbutton
					href="./zkcalendars.jsp" label="ZK Calendar Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Spreadsheet Demo: <z:toolbarbutton
					href="./zkspreadsheet.jsp" label="Spreadsheet Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					ZK Fusionchart Demo: <z:toolbarbutton
					href="./fusionchart.jsp" label="Fusionchart Demo" />
			</z:hbox></div>
			<div style="margin-top: 10px"><z:hbox>
					More Test: <z:toolbarbutton
					href="./index2.jsp" label="page2" />
			</z:hbox></div>
		</z:vbox>
	</z:window>
</z:page>
</body>
</html>
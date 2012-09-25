<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK Fusionchart</title>
</head>
<body>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
	<h2>ZK Fusionchart</h2>
	<z:window height="100%">
	<z:zscript>
		import org.zkoss.fusionchart.*;
		import org.zkoss.fusionchart.FusionchartCategoryModel;
		import org.zkoss.fusionchart.FusionchartCategoryModel.FusionchartSeries;
		import org.zkoss.fusionchart.FusionchartCategoryModel.FusionchartCategory;
		FusionchartCategoryModel catmodel = new FusionchartCategoryModel();
		FusionchartSeries y2001 = new FusionchartSeries("2001", "#00FF00");
		FusionchartSeries y2002 = new FusionchartSeries("2002", "#EDEF0E");
		FusionchartCategory Q1 = new FusionchartCategory("Q1");
		FusionchartCategory Q2 = new FusionchartCategory("Q2", "Q2 ....", false);
		FusionchartCategory Q3 = new FusionchartCategory("Q3", "Q3 ....");
		FusionchartCategory Q4 = new FusionchartCategory("Q4", "Q4 good");
		catmodel.setValue(y2001, Q1, new Integer(20));
		catmodel.setValue(y2001, Q2, new Integer(35));
		catmodel.setValue(y2001, Q3, new Integer(40));
		catmodel.setValue(y2001, Q4, new Integer(55));
		catmodel.setValue(y2002, Q1, new Integer(40));
		catmodel.setValue(y2002, Q2, new Integer(60));
		catmodel.setValue(y2002, Q3, new Integer(70));
		catmodel.setValue(y2002, Q4, new Integer(90));
	</z:zscript>
		<z:fusionchart title="Bar Chart" type="bar" width="400" height="250"
			model="${catmodel}"  />
	</z:window>
</z:page>
</body>
</html>
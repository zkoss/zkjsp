<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Test ZK 7 Charts</h1>
	<z:page>
		<z:window apply="org.zkoss.jspdemo.charts.ChartsComposer">
			<z:charts id="chart" type="line" title="Monthly Average Temperature"
				subtitle="Source: WorldClimate.com" />
		</z:window>
	</z:page>
</body>
</html>
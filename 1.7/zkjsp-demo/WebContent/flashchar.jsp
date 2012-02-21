<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>A Flash Demo</title>
</head>
<body>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
		<h2>Flash Chart</h2>
		
		<p>Displays a set of data graphically. In this demo, change the value of the grid and the flash chart will be redrawn accordingly.</p>
		
		<z:vbox>
			<z:grid width="400px">
				<z:columns>
					<z:column label="category" width="150px" />
					<z:column label="value" />
				</z:columns>
				<z:rows>
					<z:row>
						<z:label id="c0" value="C/C++" />
						<z:decimalbox id="v0" value="21.2" constraint="no empty"
							onChange="update(0)" />
					</z:row>
					<z:row>
						<z:label id="c1" value="VB" />
						<z:decimalbox id="v1" value="10.2" constraint="no empty"
							onChange="update(1)" />
					</z:row>
					<z:row>
						<z:label id="c2" value="Java" />
						<z:decimalbox id="v2" value="40.4" constraint="no empty"
							onChange="update(2)" />
					</z:row>
					<z:row>
						<z:label id="c3" value="PHP" />
						<z:decimalbox id="v3" value="28.2" constraint="no empty"
							onChange="update(3)" />
					</z:row>
				</z:rows>
			</z:grid>
			<z:flashchart id="mychart" width="500" height="250"
				type="pie">
				<z:zscript>
					void update(int rowIndex) {
						Label lb = (Label) self.getFellow("c"+rowIndex);
						Decimalbox db = (Decimalbox) self.getFellow("v"+rowIndex);
						model.setValue(lb.value, new Double(db.getValue().doubleValue()));
				  	}  
				  	PieModel model = new SimplePieModel();
					for(int j=0; j < 4; ++j) {
						update(j);
				  	}
				  	mychart.setModel(model);
				</z:zscript>
			</z:flashchart>
		</z:vbox>
</z:page>
</body>
</html>
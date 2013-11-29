<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>Test ZK 7 Navigation component set</h1>
	<z:page>
		<z:button label="Toggle open" onClick="toggleNav()"/>
		<z:navbar id="sidebar" orient="vertical"> 
			<z:navitem
				label="Inbox" iconSclass="z-icon-inbox" /> 
			<z:navseparator/>
			<z:navitem
				label="Create Task" iconSclass="z-icon-pencil" /> 
			<z:nav id="nextActions" label="Next Actions" iconSclass="z-icon-th-list" >
				<z:navitem label="Rescue the Baby" iconSclass="z-icon-star" /> 
				<z:navitem label="Play Darts" />
			</z:nav>
		</z:navbar>
		<z:zscript>
			public void toggleNav(){
				nextActions.setOpen(!nextActions.isOpen());
			}
		</z:zscript>
	</z:page>
</body>
</html>
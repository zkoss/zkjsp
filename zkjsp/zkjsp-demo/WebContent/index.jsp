<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<z:component name="hp-link" extends="toolbarbutton" class="org.zkoss.jspdemo.ui.HPLink" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ZK JSP Tag Library Demo</title>
	<z:zkhead/>
	<style type="text/css">
		<%@include file="/resource/jspdemo.css" %>
	</style>
</head>
<body>
	<div class="container">
	
		<div class="header"><!-- ********** Header ********** -->
			<h1 id="zk-logo">
				<img src="./resource/top_zk_logo.png" title="ZK Logo" alt="ZK Logo">
			</h1>
			<h1 id="topic">ZK JSP Tags - Demonstration and Testing</h1>
		</div>
		
		<div class="messages main-block"><%-- ********** left block ********** --%>
			<div class="messageboard-container">
			<z:page>	
				<z:tabbox width="100%" height="800px" apply="org.zkoss.jspdemo.HomePageCtrl">
					<z:tabs>
						<z:tab label="Demo Cases"/>
						<z:tab label="Test Cases"/>
					</z:tabs>
					<z:tabpanels>
						<z:tabpanel style="overflow:auto" ><%-- ***** JSP Demo Cases ***** --%>
						<z:grid>
							<z:columns>
								<z:column align="right" />
								<z:column />
							</z:columns>
							<z:rows>
								<z:row>
								JSTL forEach works with ZK: 
								<z:ui tag="hp-link" url="./foreach.jsp" label="forEach Demo" />
								</z:row>
								
								<z:row>
								Use Annotation data binding : 
								<z:ui tag="hp-link" url="./databinding.jsp" label="data binding Demo" />
								</z:row>
	
								<z:row>
								Use Annotation data binding demo 2: 
								<z:ui tag="hp-link" url="./databinding2.jsp" label="data binding Demo 2" />
								</z:row>
	
								<z:row>
								Use class to define customized ZK Component: 
								<z:ui tag="hp-link" url="./useclass.jsp" label="use class Demo" />
								</z:row>
	
								<z:row>
								ZK Drag and Drop features: 
								<z:ui tag="hp-link" url="./draganddrop.jsp" label="Drag and Drop Demo" />
								</z:row>
	
								<z:row>
								Selectbox Demo: 
								<z:ui tag="hp-link" url="./selectbox.jsp" label="Selectbox Demo" />
								</z:row>
	
								<z:row>
								Biglistbox Demo: 
								<z:ui tag="hp-link" url="./biglistbox.jsp" label="Biglistbox Demo" />
								</z:row>
	
								<z:row>
								Chosenbox Demo: 
								<z:ui tag="hp-link" url="./chosenbox.jsp" label="Chosenbox Demo" />
								</z:row>
	
								<z:row>
								Combobutton Demo: 
								<z:ui tag="hp-link" url="./combobutton.jsp" label="Combobutton Demo" />
								</z:row>
	
								<z:row>
								ZK Capcha Component in jsp: 
								<z:ui tag="hp-link" url="./capcha.jsp" label="Capcha Demo" />
								</z:row>
	
								<z:row>
								ZK Window Component Mode setting in zscript: 
								<z:ui tag="hp-link" url="./windowmode.jsp" label="Window Mode Demo" />
								</z:row>
	
								<z:row>
								ZK Borderlayout component set from ZKEX: 
								<z:ui tag="hp-link" url="./borderlayout.jsp" label="Borderlayout Demo" />
								</z:row>
	
								<z:row>
								Absolutelayout Demo: 
								<z:ui tag="hp-link" url="./absolutelayout.jsp" label="Absolutelayout Demo" />
								</z:row>
	
								<z:row>
								Anchorlayout Demo: 
								<z:ui tag="hp-link" url="./anchorlayout.jsp" label="Anchorlayout Demo" />
								</z:row>
	
								<z:row>
								ZK Auxheader Demo from ZKEX: 
								<z:ui tag="hp-link" url="./auxheader.jsp" label="Auxheader Demo" />
								</z:row>
	
								<z:row>
								ZK head Demo: 
								<z:ui tag="hp-link" url="./head.jsp" label="Head Demo" />
								</z:row>
	
								<%-- <z:row>
								ZK Flashchar Demo: 
								<z:ui tag="hp-link" url="./flashchar.jsp" label="Flashchar Demo" />
								</z:row>--%>
	
								<z:row>
								ZK Frozen Demo: 
								<z:ui tag="hp-link" url="./frozen.jsp" label="Frozen Demo" />
								</z:row>
	
								<z:row>
								ZK hlayout and vlayout Demo: 
								<z:ui tag="hp-link" url="./hlayoutAndVlayout.jsp" label="hlayout and vlayout Demo" />
								</z:row>
	
								<z:row>
								ZK hlayout and vlayout Demo: 
								<z:ui tag="hp-link" url="./doublespinner.jsp" label="Doublespinner Demo" />
								</z:row>
	
								<z:row>
								ZK Colorbox Demo: 
								<z:ui tag="hp-link" url="./colorbox.jsp" label="Colorbox Demo" />
								</z:row>
	
								<z:row>
								ZK Calendar Demo: 
								<z:ui tag="hp-link" url="./zkcalendars.jsp" label="ZK Calendar Demo" />
								</z:row>
	
								<z:row>
								ZK Spreadsheet Demo: 
								<z:ui tag="hp-link" url="./zkspreadsheet.jsp" label="Spreadsheet Demo" />
								</z:row>
	
								<z:row>
								ZK Pivottable Demo: 
								<z:ui tag="hp-link" url="./pivottable.jsp" label="Pivottable Demo" />
								</z:row>
	
								<z:row>
								ZK Fusionchart Demo: 
								<z:ui tag="hp-link" url="./fusionchart.jsp" label="Fusionchart Demo" />
								</z:row>
								<%-- ***** add new Demo case here! ***** --%>
							</z:rows>
						</z:grid>
						</z:tabpanel>
						
						<z:tabpanel style="overflow:auto"><%-- ZK JSP Full Function Tests --%>
							<z:grid>
								<z:columns>
									<z:column />
								</z:columns>							
								<z:rows>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/gmap/index.jsp" label="gmap/index.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/2009/paging.jsp" label="paging.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/attribute_trim.jsp" label="attribute_trim.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/B50-3301322.jsp" label="B50-3301322.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/B50-3301371.jsp" label="B50-3301371.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/bug_1997938.jsp" label="bug_1997938.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/compose.jsp" label="compose.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/composer.jsp" label="composer.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/createComponents.jsp" label="createComponents.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/customattrs.jsp" label="customattrs.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/customize.jsp" label="customize.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/dynamic_comp.jsp" label="dynamic_comp.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/forward.jsp" label="forward.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/include.jsp" label="include.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/include3.jsp" label="include3.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/macro.jsp" label="macro.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/macrotest.jsp" label="macrotest.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/pathtest.jsp" label="pathtest.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/seo_systemwidecomposer.jsp" label="seo_systemwidecomposer.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/staticcall.jsp" label="staticcall.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/systemwideinitiator.jsp" label="systemwideinitiator.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/testNative.jsp" label="testNative.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/twinpage.jsp" label="twinpage.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/urlpath.jsp" label="urlpath.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/variableresolver.jsp" label="variableresolver.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/zsinit.jsp" label="zsinit.jsp" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/binding/helloMVVM.jsp" label="hello MVVM" />
								</z:row>
								<z:row>
										<z:ui tag="hp-link"
										url="./test/bug/zkjsp-11.jsp" label="zkjsp-11.jsp" />
								</z:row>
								<z:row>
									<z:ui tag="hp-link" url="./test/navigation.jsp"
										label="navigation.jsp" />
								</z:row>
										<%-- ***** add new test case here! ***** --%>
								</z:rows>
							</z:grid>
						</z:tabpanel>
					</z:tabpanels>
				</z:tabbox>
			</z:page>				
		</div>
	</div>
	<div class="demoContent main-block" ><!-- ********** right block ********** -->
		<z:page>
			<z:toolbarbutton id="openNewBrowserBtn" label="none" href="/"/>
			<z:iframe id="demoIFrame" width="100%" height="800px" use="org.zkoss.jspdemo.ui.HomePageIframe"/>
		</z:page>	
		
	</div>
		
		
	</div><!-- ***** end of container ****** -->

			



</body>
</html>
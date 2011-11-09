<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SEORenderer with System-wide composer</title>
</head>
<body>


<z:page>
<z:zscript>
	public void bookmarkOne(Listbox lb1, Listbox lb2){
		desktop.setBookmark("lb1");
		lb1.setVisible(true);
		lb2.setVisible(false);
	}
	public void bookmarkTwo(Listbox lb1, Listbox lb2){
		desktop.setBookmark("lb2");
		lb2.setVisible(true);
		lb1.setVisible(false);
	}
</z:zscript>

<z:window id="mainPanel" width="600px"  title="SEORenderer with System-wide composer"  border="normal" >
	<z:caption id="mainCap" label="click button to change bookmark">
		
	</z:caption>
	click each button once below, refresh the page, view page source,
	you should see some code like <br />
	&lt;div id="oVCQ0"&gt;&lt;a href="/zuljsp//localhost:8080/zuljsp/test/seo_systemwidecomposer.jsp?bk=lb2"&gt;lb2&lt;/a&gt;&lt;a href="/zuljsp//localhost:8080/zuljsp/test/seo_systemwidecomposer.jsp?bk=lb1"&gt;lb1&lt;/a&gt;&lt;/div&gt;
	<br />
	<!-- references:
			SEOComposer.java
			SEODesktopInit.java
			SEORenderer.java
			execute SEORenderer in PageRenderer.java render method
			
			settings:
			<!-- vvvv for SEORenderer vvvv --
				...
			<!-- ^^^^ for SEORenderer ^^^^ -- in zk.xml
			
			http://blog.zkoss.org/index.php/2011/03/17/make-zk-application-work-with-seo/				
	 -->
	<br />
	<z:listbox id="lb1">
		<z:listitem>
			<z:listcell label="AA"/>
		</z:listitem>
	</z:listbox>
	<z:listbox id="lb2">
		<z:listitem>
			<z:listcell label="BB"/>
		</z:listitem>
	</z:listbox>
	<z:button label="to lb1" onClick='bookmarkOne(lb1, lb2)'/>
	<z:button label="to lb2" onClick='bookmarkTwo(lb1, lb2)'/>
</z:window>
</z:page>
</body>
</html>
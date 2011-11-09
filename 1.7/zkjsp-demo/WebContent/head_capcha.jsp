<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z" %>
<z:page zscriptLanguage="java"><%-- this is Jsp valid Comment, do not use XML comment. --%>
		<h2>ZK Jsp Capcha Demo</h2>
		
		<p>A simple Capcha component demo. <br/>Same as the demo in zkdemo site.</p>
		<z:window id="win1" title="ZK is best!!!" border="normal" width="280px">
			<z:vbox>
				<z:captcha id="cpa" length="5" width="200px" height="50px"/>
				<z:hbox>
					<z:button label="Regenerate" onClick="cpa.randomValue(); val.value=cpa.value;"/>
					:<z:label id="val"/>
				</z:hbox>
				<z:hbox>Assign one: <z:textbox onChange="cpa.value = self.value; val.value=self.value;"/></z:hbox>
				<z:zscript>val.value=cpa.value;</z:zscript>
			</z:vbox>
		</z:window>
</z:page>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="zk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <zk:zkhead />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello ZK!</title>
    </head>
    <body>
        <zk:page zscriptLanguage="java">
            <zk:window border="normal">
			<zk:tabbox>
				<zk:tabs>
					<zk:tab label="Some Title">
					</zk:tab>
					<zk:tab>
						<zk:label value="Notify Party Details"></zk:label>
					</zk:tab>				
				</zk:tabs>
				<zk:tabpanels>
					<zk:tabpanel>
						<zk:label value="Notify Party Details"></zk:label>
							<zk:listbox apply="org.zkoss.jspdemo.bug.FruitProvider"> 
								<zk:listhead> 
									<zk:listheader label="Name" sort="auto" /> 
									<zk:listheader label="Weight" sort="auto" /> 
								</zk:listhead> 
								<zk:template name="model"> 
									<zk:listitem>
										<zk:listcell label="${each[0]}" /> 
										<zk:listcell label="${each[1]}" /> 
									</zk:listitem> 
								</zk:template>
						</zk:listbox> 
					</zk:tabpanel>
					<zk:tabpanel>
						<zk:div height="200px" width="200px" style="background-color: yellow;">Test</zk:div>
					</zk:tabpanel>			
				</zk:tabpanels>
			</zk:tabbox>
		</zk:window>
        </zk:page>
    </body>
</html>
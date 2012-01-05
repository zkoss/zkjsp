<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.zkoss.org/jsp/zul" prefix="z"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Combobutton Demo</title>
</head>
<body>
<z:page zscriptLanguage="java">
	You can change background of listitem by click an item then click combobutton,<br />
	change selected color by open dropdown and choose color. 
	<z:window position="center" mode="overlapped">
		<z:hbox>
			<z:div width="50px" height="10px"></z:div>
			<z:combobutton id="cbbtn" orient="vertical" label="color" image="img/trans_img.png">
				<z:attribute name="onClick">
					Listitem li = box.getSelectedItem();
					if (li != null) {
						String color = cp.getContent().split("=")[1];
						li.setStyle("background-color: " + color + ";");
						li.setSelected(false);
					}
				</z:attribute>
				<z:attribute name="onCreate">
					String cmd = "jq('$cbbtn').find('img').css('background-color', '#029BCB')";
					Clients.evalJavaScript(cmd);
				</z:attribute>
				<z:menupopup>
					<z:menu id="cp" label="Color Picker" content="#color=#029BCB">
						<z:attribute name="onChange">
							String cmd = "jq('$cbbtn').find('img').css('background-color', '"+event.value+"')";
							Listitem li = box.getSelectedItem();
							if (li != null) {
								li.setStyle("background-color: " + event.value + ";");
								li.setSelected(false);
							}
							Clients.evalJavaScript(cmd);
						</z:attribute>
					</z:menu>
				</z:menupopup>
			</z:combobutton>
		</z:hbox>
	
		<z:listbox id="box" width="200px">
			<z:listhead sizable="true">
				<z:listheader label="name" sort="auto" />
				<z:listheader label="gender" sort="auto" />
			</z:listhead>
			<z:listitem>
				<z:listcell label="Mary" />
				<z:listcell label="FEMALE" />
			</z:listitem>
			<z:listitem>
				<z:listcell label="John" />
				<z:listcell label="MALE" />
			</z:listitem>
			<z:listitem>
				<z:listcell label="Jane" />
				<z:listcell label="FEMALE" />
			</z:listitem>
			<z:listitem>
				<z:listcell label="Henry" />
				<z:listcell label="MALE" />
			</z:listitem>
			<z:listitem>
				<z:listcell label="Tony" />
				<z:listcell label="MALE" />
			</z:listitem>
		</z:listbox>
	</z:window>
</z:page>
</body>
</html>
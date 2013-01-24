/**
 * 
 */
package org.zkoss.jspdemo.bug;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;

/**
 * @author Ian YT Tsai (zanyking)
 *
 */
public class BugFix_11_Vm {

	private String _textboxStr = "text box asdas";
	private String _labelStr = "label";
	
	public void setTextboxStr (String textboxStr) {
		System.out.println("do BugFix_11_Vm setTextboxStr "+_textboxStr);
		_textboxStr = textboxStr;
	}
	public String getTextboxStr () {
		return _textboxStr;
	}
	
	public void setLabelStr (String labelStr) {
		_labelStr = labelStr;
	}
	public String getLabelStr () {
		return _labelStr;
	}
	
	@Command @NotifyChange({"textboxStr", "labelStr"})
	public void sayHello () {
		_labelStr = _textboxStr;
	}
	
	@Command
	public void doTextChange(){
		System.out.println("do BugFix_11_Vm _textboxStr change!!!! _textboxStr="+_textboxStr);
	}
}

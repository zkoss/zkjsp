package org.zkoss.jspdemo;

import org.zkoss.zul.Window;

public class MyWindow2 extends Window{
	public void onOK() {
        System.out.println("========= OK ========");
        this.setTitle("onOK is fired!!!");
    }
    public void onCancel(){
       System.out.println("========== Cancel ======");
       this.setTitle("onCancel is fired!!!");
    }
}

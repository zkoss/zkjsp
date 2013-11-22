/**
 * 
 */
package org.zkoss.jspdemo.bean;

import java.util.Calendar;

/**
 * @author ian
 *
 */
public class Comment {
	
	private String text;
	private int voteValue;
	private String userName;
	private Calendar postTime;
	
	public Calendar getPostTime() {
		return postTime;
	}
	public void setPostTime(Calendar postTime) {
		this.postTime = postTime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getVoteValue() {
		return voteValue;
	}
	public void setVoteValue(int voteValue) {
		this.voteValue = voteValue;
	}
	
	
	public Comment(String text, int voteValue, String userName, Calendar postTime) {
		super();
		this.text = text;
		this.voteValue = voteValue;
		this.userName = userName;
		this.postTime = postTime;
	}

}

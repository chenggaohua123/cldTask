package com.fhtpay.task.model.warn;

public class MessageInfo {
	private String content;
	private String mobile;
	private String sendDate;
	private String sendTime;
	private String postFixNum;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getPostFixNum() {
		return postFixNum;
	}
	public void setPostFixNum(String postFixNum) {
		this.postFixNum = postFixNum;
	}
}

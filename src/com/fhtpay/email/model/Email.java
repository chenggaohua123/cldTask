package com.fhtpay.email.model;

public class Email {

	private String tradeNo;
	private String sendType;
	// 邮件发送授权号
	private String authorizedCode;
	
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getAuthorizedCode() {
		return authorizedCode;
	}
	public void setAuthorizedCode(String authorizedCode) {
		this.authorizedCode = authorizedCode;
	}

}

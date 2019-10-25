package com.fhtpay.transaction.model;

public class EmailSendInfo {
	private String id;
	private String tradeNo;
	private int sendTypeId;
	private String sendType;
	private String sendService;
	private int status;
	private String sendDate;
	private String emailSubject;
	private String merNo;
	private String terNo;
	
	
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getTerNo() {
		return terNo;
	}
	public void setTerNo(String terNo) {
		this.terNo = terNo;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSendService() {
		return sendService;
	}
	public void setSendService(String sendService) {
		this.sendService = sendService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public int getSendTypeId() {
		return sendTypeId;
	}
	public void setSendTypeId(int sendTypeId) {
		this.sendTypeId = sendTypeId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
	
}

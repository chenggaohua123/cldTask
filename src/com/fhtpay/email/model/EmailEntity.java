package com.fhtpay.email.model;

import java.sql.Timestamp;

import javax.mail.internet.InternetAddress;

public class EmailEntity {

	private Integer id;
	private Integer emailAcountId;
	private String sendType;
	private String sendDesc;
	private String emailHost;
	private String emailAcount;
	private String emailPassword;
	private String emailPort;
	private String emailSendTo;
	private String emailSubject;
	private String templateName;
	private Timestamp acountLastUpdatedDate;
	private String merNo;
	private int sendCount;
	private InternetAddress[] bcc;
	
	public int getSendCount() {
		return sendCount;
	}
	public void setSendCount(int sendCount) {
		this.sendCount = sendCount;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmailAcountId() {
		return emailAcountId;
	}
	public void setEmailAcountId(Integer emailAcountId) {
		this.emailAcountId = emailAcountId;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public String getSendDesc() {
		return sendDesc;
	}
	public void setSendDesc(String sendDesc) {
		this.sendDesc = sendDesc;
	}
	public String getEmailHost() {
		return emailHost;
	}
	public void setEmailHost(String emailHost) {
		this.emailHost = emailHost;
	}
	public String getEmailAcount() {
		return emailAcount;
	}
	public void setEmailAcount(String emailAcount) {
		this.emailAcount = emailAcount;
	}
	public String getEmailPassword() {
		return emailPassword;
	}
	public void setEmailPassword(String emailPassword) {
		this.emailPassword = emailPassword;
	}
	public String getEmailPort() {
		return emailPort;
	}
	public void setEmailPort(String emailPort) {
		this.emailPort = emailPort;
	}
	public String getEmailSendTo() {
		return emailSendTo;
	}
	public void setEmailSendTo(String emailSendTo) {
		this.emailSendTo = emailSendTo;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Timestamp getAcountLastUpdatedDate() {
		return acountLastUpdatedDate;
	}
	public void setAcountLastUpdatedDate(Timestamp acountLastUpdatedDate) {
		this.acountLastUpdatedDate = acountLastUpdatedDate;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public InternetAddress[] getBcc() {
		return bcc;
	}
	public void setBcc(InternetAddress[] bcc) {
		this.bcc = bcc;
	}
}
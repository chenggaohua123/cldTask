package com.fhtpay.email.model;

import java.sql.Timestamp;

/**
 * GwEmailSendtype entity. @author MyEclipse Persistence Tools
 */

public class GwEmailSendtype {

	private Integer id;
	private String sendType;
	private String sendDesc;
	private String emailSubject;
	private Integer createdBy;
	private Timestamp createdDate;
	private Integer lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	private String enableFlag;

	// Constructors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSendType() {
		return this.sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getSendDesc() {
		return this.sendDesc;
	}

	public void setSendDesc(String sendDesc) {
		this.sendDesc = sendDesc;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Timestamp getLastUpdatedDate() {
		return this.lastUpdatedDate;
	}

	public void setLastUpdatedDate(Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getEnableFlag() {
		return this.enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

}
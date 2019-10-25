package com.fhtpay.email.model;

/**
 * GwEmailSendRelation entity. @author MyEclipse Persistence Tools
 */

public class GwEmailSendRelation {

	private Integer id;
	private Integer emailId;
	private Integer sendTypeId;
	private Integer relationType;

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getEmailId() {
		return this.emailId;
	}
	public void setEmailId(Integer emailId) {
		this.emailId = emailId;
	}
	public Integer getSendTypeId() {
		return this.sendTypeId;
	}
	public void setSendTypeId(Integer sendTypeId) {
		this.sendTypeId = sendTypeId;
	}
	public Integer getRelationType() {
		return relationType;
	}
	public void setRelationType(Integer relationType) {
		this.relationType = relationType;
	}
}
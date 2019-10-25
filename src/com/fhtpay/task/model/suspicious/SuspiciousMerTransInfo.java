package com.fhtpay.task.model.suspicious;

import java.sql.Date;
import java.sql.Timestamp;

public class SuspiciousMerTransInfo {
	private int Id;
	private int ruleId;
	private String merNo;
	private String terNo;
	private String susType;
	private Date createDate;
	private Timestamp operatedDate;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
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
	public String getSusType() {
		return susType;
	}
	public void setSusType(String susType) {
		this.susType = susType;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Timestamp getOperatedDate() {
		return operatedDate;
	}
	public void setOperatedDate(Timestamp operatedDate) {
		this.operatedDate = operatedDate;
	}
}

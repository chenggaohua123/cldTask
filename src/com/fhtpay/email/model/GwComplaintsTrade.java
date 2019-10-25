package com.fhtpay.email.model;

import java.sql.Timestamp;

/**
 * GwComplaintsTrade entity. @author MyEclipse Persistence Tools
 */

public class GwComplaintsTrade {

	// Fields

	private Integer id;
	private String tradeNo;
	private String complaintsType;
	private String status;
	private String remark;
	private Integer createdBy;
	private Timestamp createdDate;
	private Integer lastUpdateBy;
	private Timestamp lastUpdateDate;
	private String enableFlag;
	private Timestamp deadline;
	private String complaintsDesc;
	private Integer fileId;
	private Timestamp complaintsDate;
	// 申诉内容
	private String complaintsReply;
	private String complaintsLevel;
	// 审批意见
	private String appIdea;
	private String operateStatus;
	
	private String category;
	private String categoryValue;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getComplaintsType() {
		return this.complaintsType;
	}

	public void setComplaintsType(String complaintsType) {
		this.complaintsType = complaintsType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getLastUpdateBy() {
		return this.lastUpdateBy;
	}

	public void setLastUpdateBy(Integer lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public Timestamp getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getEnableFlag() {
		return this.enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Timestamp getDeadline() {
		return this.deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public String getComplaintsDesc() {
		return this.complaintsDesc;
	}

	public void setComplaintsDesc(String complaintsDesc) {
		this.complaintsDesc = complaintsDesc;
	}

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public Timestamp getComplaintsDate() {
		return this.complaintsDate;
	}

	public void setComplaintsDate(Timestamp complaintsDate) {
		this.complaintsDate = complaintsDate;
	}

	public String getComplaintsLevel() {
		return complaintsLevel;
	}

	public void setComplaintsLevel(String complaintsLevel) {
		this.complaintsLevel = complaintsLevel;
	}

	public String getComplaintsReply() {
		return complaintsReply;
	}

	public void setComplaintsReply(String complaintsReply) {
		this.complaintsReply = complaintsReply;
	}

	public String getAppIdea() {
		return appIdea;
	}

	public void setAppIdea(String appIdea) {
		this.appIdea = appIdea;
	}
	public String getOperateStatus() {
		return operateStatus;
	}
	public void setOperateStatus(String operateStatus) {
		this.operateStatus = operateStatus;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
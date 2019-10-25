package com.fhtpay.email.model;

import java.sql.Timestamp;

/**
 * GwComplaintsTradeDetail entity. @author MyEclipse Persistence Tools
 */

public class GwComplaintsTradeDetail {

	// Fields

	private Integer id;
	private Integer complaintsId;
	private Integer fileId;
	private String complaintsDesc;
	private String remark;
	private String createdBy;
	private Timestamp createdDate;
	private Integer lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	private String enableFlag;

	// Property accessors
	private String fileName;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getComplaintsId() {
		return this.complaintsId;
	}

	public void setComplaintsId(Integer complaintsId) {
		this.complaintsId = complaintsId;
	}

	public Integer getFileId() {
		return this.fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	public String getComplaintsDesc() {
		return this.complaintsDesc;
	}

	public void setComplaintsDesc(String complaintsDesc) {
		this.complaintsDesc = complaintsDesc;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
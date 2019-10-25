package com.fhtpay.email.model;

import java.sql.Timestamp;

/**
 * SmManageCode entity. @author MyEclipse Persistence Tools
 */

public class SmManageCode {

	// Fields

	private Integer id;
	private String category;
	private String categoryKey;
	private String categoryValue;
	private Integer parentId;
	private Integer createdBy;
	private Timestamp createdDate;
	private Integer lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	private String enableFlag;

	// Constructors
	// 单选还是复选
	private String checkType;

	/** default constructor */
	public SmManageCode() {
	}

	/** minimal constructor */
	public SmManageCode(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SmManageCode(Integer id, String category, String categoryKey,
			String categoryValue, Integer createdBy, Timestamp createdDate,
			Integer lastUpdatedBy, Timestamp lastUpdatedDate, String enableFlag) {
		this.id = id;
		this.category = category;
		this.categoryKey = categoryKey;
		this.categoryValue = categoryValue;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.enableFlag = enableFlag;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategoryKey() {
		return this.categoryKey;
	}

	public void setCategoryKey(String categoryKey) {
		this.categoryKey = categoryKey;
	}

	public String getCategoryValue() {
		return this.categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

}
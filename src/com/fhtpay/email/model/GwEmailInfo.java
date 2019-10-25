package com.fhtpay.email.model;

import java.sql.Timestamp;

/**
 * GwEmailInfo entity. @author MyEclipse Persistence Tools
 */

public class GwEmailInfo {

	// Fields

	private Integer id;
	private String tel;
	private String fax;
	private String email;
	private String replayEmail;
	private String helpWebsite;
	private Timestamp createdDate;
	private Integer lastUpdatedBy;
	private Timestamp lastUpdatedDate;
	private String enableFlag;
	private String website;
	

	// Constructors

	/** default constructor */
	public GwEmailInfo() {
	}

	/** minimal constructor */
	public GwEmailInfo(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public GwEmailInfo(Integer id, String tel, String fax, String email,
			String replayEmail, String helpWebsite, Timestamp createdDate,
			Integer lastUpdatedBy, Timestamp lastUpdatedDate,
			String enableFlag, String website) {
		this.id = id;
		this.tel = tel;
		this.fax = fax;
		this.email = email;
		this.replayEmail = replayEmail;
		this.helpWebsite = helpWebsite;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
		this.enableFlag = enableFlag;
		this.website = website;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReplayEmail() {
		return this.replayEmail;
	}

	public void setReplayEmail(String replayEmail) {
		this.replayEmail = replayEmail;
	}

	public String getHelpWebsite() {
		return this.helpWebsite;
	}

	public void setHelpWebsite(String helpWebsite) {
		this.helpWebsite = helpWebsite;
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

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}



}
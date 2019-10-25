package com.fhtpay.transaction.model;

import java.math.BigDecimal;

public class SettleTypeInfo {
	private String id;
	private String merNo;
	private String terNo;
	private String settleType;
	private String settleCycle;
	private String bondCycle;
	private String settleService;
	private String enabled;
	private String createBy;
	private String createDate;
	private String lastModify;
	private String lastModifyDate;
	private String remark;
	private BigDecimal frozenPercent;
	
	
	public String getBondCycle() {
		return bondCycle;
	}
	public void setBondCycle(String bondCycle) {
		this.bondCycle = bondCycle;
	}
	public BigDecimal getFrozenPercent() {
		return frozenPercent;
	}
	public void setFrozenPercent(BigDecimal frozenPercent) {
		this.frozenPercent = frozenPercent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getSettleType() {
		return settleType;
	}
	public void setSettleType(String settleType) {
		this.settleType = settleType;
	}
	public String getSettleCycle() {
		return settleCycle;
	}
	public void setSettleCycle(String settleCycle) {
		this.settleCycle = settleCycle;
	}
	public String getSettleService() {
		return settleService;
	}
	public void setSettleService(String settleService) {
		this.settleService = settleService;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getLastModify() {
		return lastModify;
	}
	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}
	public String getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(String lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

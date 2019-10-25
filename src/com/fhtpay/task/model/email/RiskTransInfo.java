package com.fhtpay.task.model.email;

import java.sql.Timestamp;

public class RiskTransInfo {
	private int id;
	private String merNo;
	private String terNo;
	private String tradeNo;
	private String orderNo;
	private String website;
	private String ruleId;
	private Timestamp doDate;
	private Timestamp doEndDate;
	private String doReason;
	private String doStatus;
	private String respCode;
	private String respMsg;
	private String reStatus;
	private String email;
	private String merchantName;
	private String transDate;
	private String merTransAmount;
	private String merBusCurrency;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public Timestamp getDoDate() {
		return doDate;
	}
	public void setDoDate(Timestamp doDate) {
		this.doDate = doDate;
	}
	public Timestamp getDoEndDate() {
		return doEndDate;
	}
	public void setDoEndDate(Timestamp doEndDate) {
		this.doEndDate = doEndDate;
	}
	public String getDoReason() {
		return doReason;
	}
	public void setDoReason(String doReason) {
		this.doReason = doReason;
	}
	public String getDoStatus() {
		return doStatus;
	}
	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getReStatus() {
		return reStatus;
	}
	public void setReStatus(String reStatus) {
		this.reStatus = reStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getMerTransAmount() {
		return merTransAmount;
	}
	public void setMerTransAmount(String merTransAmount) {
		this.merTransAmount = merTransAmount;
	}
	public String getMerBusCurrency() {
		return merBusCurrency;
	}
	public void setMerBusCurrency(String merBusCurrency) {
		this.merBusCurrency = merBusCurrency;
	}
}

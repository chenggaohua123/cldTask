package com.fhtpay.email.model;

import java.sql.Timestamp;


public class GwTradeRecord {
	private String tradeNo;
	private String orderNo;
	private String cardHoldEmail;
	private String merchantEmail;
	private String currency;
	private String amount;
	private String transDate;
	private String tel;
	private String fax;
	private String website;
	private String helpWebsite;
	private String replyEmail;
	private String refundAmount;
	private String merNo;
	private String terNo;
	private String currencyId;
	private Timestamp uploadTime;
	private String iogistics;
	private String trackNo;
	private String iogisticsUrl;
	private String payWebSite;
	private String acquirer;
	
	public String getAcquirer() {
		return acquirer;
	}
	public void setAcquirer(String acquirer) {
		this.acquirer = acquirer;
	}
	public String getPayWebSite() {
		return payWebSite;
	}
	public void setPayWebSite(String payWebSite) {
		this.payWebSite = payWebSite;
	}
	public String getIogisticsUrl() {
		return iogisticsUrl;
	}
	public void setIogisticsUrl(String iogisticsUrl) {
		this.iogisticsUrl = iogisticsUrl;
	}
	public Timestamp getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getIogistics() {
		return iogistics;
	}
	public void setIogistics(String iogistics) {
		this.iogistics = iogistics;
	}
	public String getTrackNo() {
		return trackNo;
	}
	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
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
	public String getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
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
	public String getCardHoldEmail() {
		return cardHoldEmail;
	}
	public void setCardHoldEmail(String cardHoldEmail) {
		this.cardHoldEmail = cardHoldEmail;
	}
	public String getMerchantEmail() {
		return merchantEmail;
	}
	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getHelpWebsite() {
		return helpWebsite;
	}
	public void setHelpWebsite(String helpWebsite) {
		this.helpWebsite = helpWebsite;
	}
	public String getReplyEmail() {
		return replyEmail;
	}
	public void setReplyEmail(String replyEmail) {
		this.replyEmail = replyEmail;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	
	
	
}
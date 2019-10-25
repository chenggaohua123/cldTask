package com.fhtpay.task.service.messageservice.util;

import java.sql.Timestamp;

public class AutoRiskInfo{
	private String tradeNo;
	private String shaCardNo;
	private String email;
	private String webSite;
	private String uid;
	private String ipAddress;
	private String police1;
	private String police2;
	private String police3;
	private String police4;
	private String respCode;
	private String respMsg;
	private Timestamp queryTime;
	
	public Timestamp getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Timestamp queryTime) {
		this.queryTime = queryTime;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getShaCardNo() {
		return shaCardNo;
	}
	public void setShaCardNo(String shaCardNo) {
		this.shaCardNo = shaCardNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebSite() {
		return webSite;
	}
	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getPolice1() {
		return police1;
	}
	public void setPolice1(String police1) {
		this.police1 = police1;
	}
	public String getPolice2() {
		return police2;
	}
	public void setPolice2(String police2) {
		this.police2 = police2;
	}
	public String getPolice3() {
		return police3;
	}
	public void setPolice3(String police3) {
		this.police3 = police3;
	}
	public String getPolice4() {
		return police4;
	}
	public void setPolice4(String police4) {
		this.police4 = police4;
	}
	
	
	
}

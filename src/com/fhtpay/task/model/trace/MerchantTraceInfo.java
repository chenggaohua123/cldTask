package com.fhtpay.task.model.trace;

import java.sql.Timestamp;

public class MerchantTraceInfo {

	private int id;
	private String tradeNo;
	private String merNo;
	private String trackNo;
	private String country;
	private Timestamp tradetime;
	private int operationStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getTrackNo() {
		return trackNo;
	}
	public void setTrackNo(String trackNo) {
		this.trackNo = trackNo;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Timestamp getTradetime() {
		return tradetime;
	}
	public void setTradetime(Timestamp tradetime) {
		this.tradetime = tradetime;
	}
	public int getOperationStatus() {
		return operationStatus;
	}
	public void setOperationStatus(int operationStatus) {
		this.operationStatus = operationStatus;
	}
	
}

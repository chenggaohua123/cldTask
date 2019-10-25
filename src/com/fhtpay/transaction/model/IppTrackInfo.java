package com.fhtpay.transaction.model;

public class IppTrackInfo {
	private String mid;
	private String site_id;
	private String oid;
	private String tracking_company;
	private String tracking_number;
	private String hash_info;
	private String api_key;
	private String tradeNo;
	
	
	
	
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getApi_key() {
		return api_key;
	}
	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public String getTracking_company() {
		return tracking_company;
	}
	public void setTracking_company(String tracking_company) {
		this.tracking_company = tracking_company;
	}
	public String getTracking_number() {
		return tracking_number;
	}
	public void setTracking_number(String tracking_number) {
		this.tracking_number = tracking_number;
	}
	public String getHash_info() {
		return hash_info;
	}
	public void setHash_info(String hash_info) {
		this.hash_info = hash_info;
	}
	
	
}

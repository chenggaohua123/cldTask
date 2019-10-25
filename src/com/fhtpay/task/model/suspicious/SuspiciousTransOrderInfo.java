package com.fhtpay.task.model.suspicious;

public class SuspiciousTransOrderInfo {
	private int Id;
	private String tradeNo;
	private int syspId;
	private String info;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public int getSyspId() {
		return syspId;
	}
	public void setSyspId(int syspId) {
		this.syspId = syspId;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
}

package com.fhtpay.transaction.model;

public class DuplicateTransCount {
	private int successDuplicate;
	private int failDuplicate;
	private String respCode;
	/**
	 * 交易日期
	 */
	private String doDate;
	
	
	public String getDoDate() {
		return doDate;
	}
	public void setDoDate(String doDate) {
		this.doDate = doDate;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public int getSuccessDuplicate() {
		return successDuplicate;
	}
	public void setSuccessDuplicate(int successDuplicate) {
		this.successDuplicate = successDuplicate;
	}
	public int getFailDuplicate() {
		return failDuplicate;
	}
	public void setFailDuplicate(int failDuplicate) {
		this.failDuplicate = failDuplicate;
	}
	
	
}

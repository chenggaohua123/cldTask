package com.fhtpay.task.model.suspicious;

public class SuspiciousValueInfo {
	private String colKeyName;
	private String colValueName;
	private String colKeyValue;
	private String colValue;

	public String getColValue() {
		return colValue;
	}
	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
	public String getColKeyValue() {
		return colKeyValue;
	}
	public void setColKeyValue(String colKeyValue) {
		this.colKeyValue = colKeyValue;
	}
	public String getColKeyName() {
		return colKeyName;
	}
	public void setColKeyName(String colKeyName) {
		this.colKeyName = colKeyName;
	}
	public String getColValueName() {
		return colValueName;
	}
	public void setColValueName(String colValueName) {
		this.colValueName = colValueName;
	}
}

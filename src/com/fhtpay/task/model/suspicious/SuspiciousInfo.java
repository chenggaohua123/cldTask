package com.fhtpay.task.model.suspicious;

public class SuspiciousInfo {
	private String tableName;
	private SuspiciousMerTerInfo mer;
	private SuspiciousValueInfo values;
	private String paramValue;
	private String paramRuleValue;
	
	
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getParamRuleValue() {
		return paramRuleValue;
	}
	public void setParamRuleValue(String paramRuleValue) {
		this.paramRuleValue = paramRuleValue;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public SuspiciousMerTerInfo getMer() {
		return mer;
	}
	public void setMer(SuspiciousMerTerInfo mer) {
		this.mer = mer;
	}
	public SuspiciousValueInfo getValues() {
		return values;
	}
	public void setValues(SuspiciousValueInfo values) {
		this.values = values;
	}
}

package com.fhtpay.task.model.suspicious;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ParamInfo {
	private String paramId;
	private String paramName;
	private String paramDescName;
	private Timestamp createDate;
	private String createBy;
	private String remark;
	private String type;
	private String comFrom;
	private String status;
	private String stringValue;
	private List<String> listValue;
	private Map<String,String> tableValue;
	private List<ParamValueInfo> paramValuesList;
	private String tableName;
	private String colValueName;
	private String colKeyName;
	private String colKeyValue;
	private String colValue;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColValueName() {
		return colValueName;
	}

	public void setColValueName(String colValueName) {
		this.colValueName = colValueName;
	}

	public String getColKeyName() {
		return colKeyName;
	}

	public void setColKeyName(String colKeyName) {
		this.colKeyName = colKeyName;
	}

	public String getColKeyValue() {
		return colKeyValue;
	}

	public void setColKeyValue(String colKeyValue) {
		this.colKeyValue = colKeyValue;
	}

	public String getColValue() {
		return colValue;
	}

	public void setColValue(String colValue) {
		this.colValue = colValue;
	}

	public List<ParamValueInfo> getParamValuesList() {
		return paramValuesList;
	}

	public void setParamValuesList(List<ParamValueInfo> paramValuesList) {
		this.paramValuesList = paramValuesList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public List<String> getListValue() {
		return listValue;
	}

	public void setListValue(List<String> listValue) {
		this.listValue = listValue;
	}

	public Map<String, String> getTableValue() {
		return tableValue;
	}

	public void setTableValue(Map<String, String> tableValue) {
		this.tableValue = tableValue;
	}

	public String getComFrom() {
		return comFrom;
	}

	public void setComFrom(String comFrom) {
		this.comFrom = comFrom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamDescName() {
		return paramDescName;
	}

	public void setParamDescName(String paramDescName) {
		this.paramDescName = paramDescName;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

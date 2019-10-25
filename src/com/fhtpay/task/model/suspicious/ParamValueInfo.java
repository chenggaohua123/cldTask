package com.fhtpay.task.model.suspicious;

import java.io.Serializable;

public class ParamValueInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id;
	private String paramId;
	private String value;
	private String tableName;
	private String colKey;
	private String colValue;
	private String colKeyValue;
	private String colValueValue;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParamId() {
		return paramId;
	}

	public void setParamId(String paramId) {
		this.paramId = paramId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColKey() {
		return colKey;
	}

	public void setColKey(String colKey) {
		this.colKey = colKey;
	}

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

	public String getColValueValue() {
		return colValueValue;
	}

	public void setColValueValue(String colValueValue) {
		this.colValueValue = colValueValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}

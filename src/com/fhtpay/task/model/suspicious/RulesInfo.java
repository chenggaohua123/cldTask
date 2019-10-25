package com.fhtpay.task.model.suspicious;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RulesInfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String ruleId;
	private String ruleName;
	private String paramId;
	private String paramDescName;
	private String ruleParamValueId;
	private String ruleParamValueDescName;
	private String processClassId;
	private String status;
	private Timestamp createDate;
	private String createBy;
	private String processClassName;
	private ParamInfo paramInfo;
	private ParamInfo ruleParamInfo;
	private RuleProcessClass processClass;
	private String action;
	
	public RuleProcessClass getProcessClass() {
		return processClass;
	}
	public void setProcessClass(RuleProcessClass processClass) {
		this.processClass = processClass;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getParamDescName() {
		return paramDescName;
	}
	public void setParamDescName(String paramDescName) {
		this.paramDescName = paramDescName;
	}
	public String getRuleParamValueDescName() {
		return ruleParamValueDescName;
	}
	public void setRuleParamValueDescName(String ruleParamValueDescName) {
		this.ruleParamValueDescName = ruleParamValueDescName;
	}
	public String getProcessClassName() {
		return processClassName;
	}
	public void setProcessClassName(String processClassName) {
		this.processClassName = processClassName;
	}
	public ParamInfo getParamInfo() {
		return paramInfo;
	}
	public void setParamInfo(ParamInfo paramInfo) {
		this.paramInfo = paramInfo;
	}
	public ParamInfo getRuleParamInfo() {
		return ruleParamInfo;
	}
	public void setRuleParamInfo(ParamInfo ruleParamInfo) {
		this.ruleParamInfo = ruleParamInfo;
	}
	public String getProcessClassId() {
		return processClassId;
	}
	public void setProcessClassId(String processClassId) {
		this.processClassId = processClassId;
	}
	private List<String> ruleParamValus;
	private Map<String, String> ruleParamValuesMap;
	
	public List<String> getRuleParamValus() {
		return ruleParamValus;
	}
	public void setRuleParamValus(List<String> ruleParamValus) {
		this.ruleParamValus = ruleParamValus;
	}
	public Map<String, String> getRuleParamValuesMap() {
		return ruleParamValuesMap;
	}
	public void setRuleParamValuesMap(Map<String, String> ruleParamValuesMap) {
		this.ruleParamValuesMap = ruleParamValuesMap;
	}
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getParamId() {
		return paramId;
	}
	public void setParamId(String paramId) {
		this.paramId = paramId;
	}
	public String getRuleParamValueId() {
		return ruleParamValueId;
	}
	public void setRuleParamValueId(String ruleParamValueId) {
		this.ruleParamValueId = ruleParamValueId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	
}

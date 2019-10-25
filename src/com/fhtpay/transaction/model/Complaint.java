package com.fhtpay.transaction.model;

import java.sql.Timestamp;

/** 调查单 */
public class Complaint{
	private Integer id;
	private String tradeNo;
	/** 商户号 */
	private String merNo;
	/** 调查单类型 */
	private String complaintType;
	/** 调查单类型value */
	private String complaintTypeValue;
	/** 处理截止日期 */
	private String deadline;
	/** 状态 */
	private String status;
	/** 0,调查单，1拒付；2持卡人投诉 */
	private int type;
	/** 补充说明 */
	private String remark;
	/** 调查日期 */
	private String complaintDate;
	/** 创建人 */
	private String createdBy;
	/** 创建时间 */
	private Timestamp createdDate;
	/** 修改人 */
	private String lastUpdateBy;
	/** 修改时间 */
	private Timestamp lastUpdateDate;
	/** 批量操作类型 */
	private String operationType;
	/** 批量操作ID串 */
	private String ids;
	/** 投诉级别 */
	private String complaintLevel;
	/** 拒付单CPD日期 */
	private String CPDDate;
	/** 订单号 */
	private String orderNo;
	/** 网站 */
	private String payWebSite;
	/** 交易币种 */
	private String merBusCurrency;
	/** 交易金额 */
	private String merTransAmount;
	/** 上传文件路径 */
	private String filePath;
	private int merchantType;
	/** 拒付预警费 */
	private double amount;
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(int merchantType) {
		this.merchantType = merchantType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getPayWebSite() {
		return payWebSite;
	}
	public void setPayWebSite(String payWebSite) {
		this.payWebSite = payWebSite;
	}
	public String getMerBusCurrency() {
		return merBusCurrency;
	}
	public void setMerBusCurrency(String merBusCurrency) {
		this.merBusCurrency = merBusCurrency;
	}
	public String getMerTransAmount() {
		return merTransAmount;
	}
	public void setMerTransAmount(String merTransAmount) {
		this.merTransAmount = merTransAmount;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getCPDDate() {
		return CPDDate;
	}
	public void setCPDDate(String cPDDate) {
		CPDDate = cPDDate;
	}
	public String getComplaintLevel() {
		return complaintLevel;
	}
	public void setComplaintLevel(String complaintLevel) {
		this.complaintLevel = complaintLevel;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getComplaintTypeValue() {
		return complaintTypeValue;
	}
	public void setComplaintTypeValue(String complaintTypeValue) {
		this.complaintTypeValue = complaintTypeValue;
	}
	private String idList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getComplaintType() {
		return complaintType;
	}
	public void setComplaintType(String complaintType) {
		this.complaintType = complaintType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMerNo() {
		return merNo;
	}
	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public Timestamp getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Timestamp lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	public String getComplaintDate() {
		return complaintDate;
	}
	public void setComplaintDate(String complaintDate) {
		this.complaintDate = complaintDate;
	}
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
}

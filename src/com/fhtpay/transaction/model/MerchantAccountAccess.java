package com.fhtpay.transaction.model;

/**
 * 商户账户出入账
 */
public class MerchantAccountAccess {
	private String id;
	/*** 提现类型 */
	private String cashType;
	private String accountID;
	private String currency;
	//商户结算金额合计
	private double totalAmount;
	private double cashAmount;
	private double feeAmount;
	private double bondAmount;
	private double bondCashAmount;
	private double frozenAmount;
	private String createDate;
	private String remark;
	private String merNo;
	private String terNo;
	private String accountType;
	private double amount;
	private String moneyDate;
	private String moenyBy;
	private String moenyRemark;
	/*** 退款处理费 */
	private double refundFee;
	/*** 退款免手续费*/
	private double refundTransFee;
	/*** 拒付处理费*/
	private double disFee;

	public double getRefundFee() {
		return refundFee;
	}

	public void setRefundFee(double refundFee) {
		this.refundFee = refundFee;
	}

	public double getRefundTransFee() {
		return refundTransFee;
	}

	public void setRefundTransFee(double refundTransFee) {
		this.refundTransFee = refundTransFee;
	}

	public double getDisFee() {
		return disFee;
	}

	public void setDisFee(double disFee) {
		this.disFee = disFee;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getTerNo() {
		return terNo;
	}

	public void setTerNo(String terNo) {
		this.terNo = terNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getMoneyDate() {
		return moneyDate;
	}

	public void setMoneyDate(String moneyDate) {
		this.moneyDate = moneyDate;
	}

	public String getMoenyBy() {
		return moenyBy;
	}

	public void setMoenyBy(String moenyBy) {
		this.moenyBy = moenyBy;
	}

	public String getMoenyRemark() {
		return moenyRemark;
	}

	public void setMoenyRemark(String moenyRemark) {
		this.moenyRemark = moenyRemark;
	}

	public String getCashType() {
		return cashType;
	}

	public void setCashType(String cashType) {
		this.cashType = cashType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public double getFeeAmount() {
		return feeAmount;
	}

	public void setFeeAmount(double feeAmount) {
		this.feeAmount = feeAmount;
	}

	public double getBondAmount() {
		return bondAmount;
	}

	public void setBondAmount(double bondAmount) {
		this.bondAmount = bondAmount;
	}

	public double getBondCashAmount() {
		return bondCashAmount;
	}

	public void setBondCashAmount(double bondCashAmount) {
		this.bondCashAmount = bondCashAmount;
	}

	public double getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(double frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

}

package com.fhtpay.transaction.model;


public class MerchantAccount {
	private String id;
	private String merNo;
	private String terNo;
	private String currency;
	private double totalAmount;
	private double cashAmount;
	private double bondAmount;
	private double bondCashAmount;
	private double frozenAmount;
	private double transFrozenAmount;
	
	
	
	public double getTransFrozenAmount() {
		return transFrozenAmount;
	}
	public void setTransFrozenAmount(double transFrozenAmount) {
		this.transFrozenAmount = transFrozenAmount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
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

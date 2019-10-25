package com.fhtpay.task.model;


public class PoolSettleInfo {

	private int id;
	private String merNo;
	private String terNo;
	private String dateStr;
	private int dateInt;
	private String currency;
	private int successCount;
	private Double settleAmount;
	private Double feeAmount;
	private Double singleAmount;
	private Double bondAmount;
	private Double refundAmount;
	private Double disAmount;
	private Double thawAmount;
	private Double froznAmount;
	private Double cashAmount;
	private Double bondCashAmount;
	private Double totalAmount;
	
	
	private Double balance;
	private Double failFee;
	
	private Double transDebit;
	private Double transBack;
	private Double bondDebit;
	private Double bondBack;
	
	private Double settleChangeFrozn;
	private Double settleChangeThaw;
	
	private Double bondFrozn;
	private Double bondThaw;
	
	private Double disFine;
	private Double refundFee;
	private Double refundFeeBack;
	private Double disFee;
	
	private Double otherAmount;
	
	
	
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public void setOtherAmount(Double otherAmount) {
		this.otherAmount = otherAmount;
	}
	public int getDateInt() {
		return dateInt;
	}
	public void setDateInt(int dateInt) {
		this.dateInt = dateInt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getBondCashAmount() {
		return bondCashAmount;
	}
	public void setBondCashAmount(Double bondCashAmount) {
		this.bondCashAmount = bondCashAmount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getFailFee() {
		return failFee;
	}
	public void setFailFee(Double failFee) {
		this.failFee = failFee;
	}
	public Double getTransDebit() {
		return transDebit;
	}
	public void setTransDebit(Double transDebit) {
		this.transDebit = transDebit;
	}
	public Double getTransBack() {
		return transBack;
	}
	public void setTransBack(Double transBack) {
		this.transBack = transBack;
	}
	public Double getBondDebit() {
		return bondDebit;
	}
	public void setBondDebit(Double bondDebit) {
		this.bondDebit = bondDebit;
	}
	public Double getBondBack() {
		return bondBack;
	}
	public void setBondBack(Double bondBack) {
		this.bondBack = bondBack;
	}
	public Double getSettleChangeFrozn() {
		return settleChangeFrozn;
	}
	public void setSettleChangeFrozn(Double settleChangeFrozn) {
		this.settleChangeFrozn = settleChangeFrozn;
	}
	public Double getSettleChangeThaw() {
		return settleChangeThaw;
	}
	public void setSettleChangeThaw(Double settleChangeThaw) {
		this.settleChangeThaw = settleChangeThaw;
	}
	public Double getBondFrozn() {
		return bondFrozn;
	}
	public void setBondFrozn(Double bondFrozn) {
		this.bondFrozn = bondFrozn;
	}
	public Double getBondThaw() {
		return bondThaw;
	}
	public void setBondThaw(Double bondThaw) {
		this.bondThaw = bondThaw;
	}
	public Double getDisFine() {
		return disFine;
	}
	public void setDisFine(Double disFine) {
		this.disFine = disFine;
	}
	public Double getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}
	public Double getRefundFeeBack() {
		return refundFeeBack;
	}
	public void setRefundFeeBack(Double refundFeeBack) {
		this.refundFeeBack = refundFeeBack;
	}
	public Double getDisFee() {
		return disFee;
	}
	public void setDisFee(Double disFee) {
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
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public Double getSettleAmount() {
		return settleAmount;
	}
	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}
	public Double getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(Double feeAmount) {
		this.feeAmount = feeAmount;
	}
	public Double getSingleAmount() {
		return singleAmount;
	}
	public void setSingleAmount(Double singleAmount) {
		this.singleAmount = singleAmount;
	}
	public Double getBondAmount() {
		return bondAmount;
	}
	public void setBondAmount(Double bondAmount) {
		this.bondAmount = bondAmount;
	}
	public Double getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}
	public Double getDisAmount() {
		return disAmount;
	}
	public void setDisAmount(Double disAmount) {
		this.disAmount = disAmount;
	}
	public Double getThawAmount() {
		return thawAmount;
	}
	public void setThawAmount(Double thawAmount) {
		this.thawAmount = thawAmount;
	}
	public Double getFroznAmount() {
		return froznAmount;
	}
	public void setFroznAmount(Double froznAmount) {
		this.froznAmount = froznAmount;
	}
	public Double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(Double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public Double getOtherAmount() {
//		this.otherAmount=failFee+transBack+bondDebit+bondBack+settleChangeFrozn+settleChangeThaw+bondFrozn
//				+bondThaw+disFine+refundFee+refundFeeBack+disFee;
		return otherAmount;
	}
	public void initOtherAmount() {
		this.otherAmount =failFee+transBack+bondDebit+bondBack+settleChangeFrozn+settleChangeThaw+bondFrozn
				+bondThaw+disFine+refundFee+refundFeeBack+disFee;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void initTotalAmount(){
		totalAmount=settleAmount     
				+feeAmount      
				+singleAmount   
				+refundAmount   
				+disAmount      
				+thawAmount     
				+froznAmount    
				+cashAmount     
				+bondCashAmount      
				+failFee        
				+transDebit     
				+transBack      
				+bondDebit      
				+bondBack       
				+settleChangeFrozn
				+settleChangeThaw            
				+bondFrozn      
				+bondThaw
				+disFine        
				+refundFee      
				+refundFeeBack  
				+disFee;         
	}
	
}

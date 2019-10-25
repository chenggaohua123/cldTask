package com.fhtpay.transaction.model;


public class ResultPayment {

	private String transType;//消费类型
	private String orderNo;//商户流水号
	private String merNo;//商户号
	private String terNo;//终端号
	private String currencyCode;//支付币种
	private String amount;//支付金额
	/** 交易流水号 */
	private String tradeNo;
	/**支付验签码 */ 	
	private String hashcode;
	//支付状态
	private String respCode;
	/** 支付信息 */
	private String respMsg;
	
	private String aquirer;
	
	
	public String getAquirer() {
		return aquirer;
	}
	public void setAquirer(String aquirer) {
		this.aquirer = aquirer;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	
	
	
}

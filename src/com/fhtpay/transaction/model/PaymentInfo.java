package com.fhtpay.transaction.model;

import java.util.List;


/*
 * 支付信息
 * */
public class PaymentInfo {
	private String transType;//消费类型
	private String transModel;//消费模式
	private String orderNo;//商户流水号
	private String merNo;//商户号
	private String terNo;//终端号
	private String currencyCode;//支付币种
	//本币币种
	private String currencyTypeT;
	private String amount;//支付金额
	private String merremark;//商户预留字段
	private String merReturnURL;//支付信息返回页面
	private String merMgrURL;//商户后台返回页面
	private String webInfo;//持卡人浏览器信息
	private String hash;//安全码
	/** 交易流水号 */
	private String tradeNo;
	
	//信用卡账单信息
	/** 卡信息 --->国家 */
	private String cardCountry;
	/** 卡信息 --->州/省 */
	private String cardState;
	/** 卡信息 --->市 */
	private String cardCity;
	/** 卡信息 --->详细地址 */
	private String cardAddress;
	/** 卡信息 --->邮编 */
	private String cardZipCode;
	/** 持卡人姓名 */
	private String cardFullName;
	/** 持卡人电话 */
	private String cardFullPhone;
	
	//收货信息 
	/** 收货信息 --->国家 */
	private String grCountry;
	/** 收货信息 --->州/省 */
	private String grState;
	/** 收货信息 --->市 */
	private String grCity;
	/** 收货信息 --->详细地址 */
	private String grAddress;
	/** 收货信息 --->邮编 */
	private String grZipCode;
	/** 收货信息 --->邮箱 */
	private String grEmail;
	/**收货电话*/
	private String grphoneNumber;
	/**收货名称*/
	private String grPerName;
	/** 货物信息JSON串 */
	private String goodsString;
	
	private String ipCountry;
	
	//卡信息
	/** 银行卡号 */
	private String cardNO;
	/** 支付密码 */
	private String password;
	/** 信用卡安全码 */
	/** 有效期，年份 */
	private String expYear;
	/** 有效期，月份 */
	private String expMonth;
	/** 卡背面的三位 */
	private String cvv;
	/** 有效期，年份 */
	private String expYearStr;
	/** 有效期，月份 */
	private String expMonthStr;
	// 发卡行
	private String issuingBank;
	/** 卡背面的三位 */
	private String c;
	/** 有效期，年份 */
	private String y;
	/** 有效期，月份 */
	private String m;
	
	
	/** 卡背面的三位 */
	private String cvvStr;
	
	private String invoiceNo;
	private String bankOrderID;
	/** 组参安全码 */ 	
	private String hashcode;
	//支付状态
	private String respCode;
	/** 支付信息 */
	private String RespMsg;
	//通道ID
	private String currencyID;
	/** 持卡人姓名 */
	private String userName;
	/** 发卡行 */
	private String issuer;
	/** 购物名 */
	private String shipName;
	/** 购物电话 */
	private String shipphone;
	/** 持卡人电话 */
	private String cardTelephone;
	/** 持卡人购物的网站Url,无http://和参数 */
	private String retURL;
	/** 持卡人Email */
	private String cardEmail;
	/** 品牌 */
	private String commodityBrand;
	/** 卡种 */
	private String cardType;
	
	/**语言*/
	private String language;
	private String returnURL;
	private String submitURL;
	private String payWebSite;
	private String payIP;
	private String apiType;// 1普通接口/2 app sdk/3快捷支付/4虚拟
	
	private String merSettleCurrency;
	private String debitType;// 借记卡的类型
	private String riskScore;
	
	private String skipToPayURL;
	/*英文账单名称*/
	private String aquirer;
	private String member;// 会员账号
	private String paidType;// 消费类型(1首次消费，2续费，3续费新卡号)
	private String firstName;
	private String lastName;
	private String orderKey;
	private int backFlag;
	
	
	
	public int getBackFlag() {
		return backFlag;
	}
	public void setBackFlag(int backFlag) {
		this.backFlag = backFlag;
	}
	public String getAquirer() {
		return aquirer;
	}
	public void setAquirer(String aquirer) {
		this.aquirer = aquirer;
	}
	public String getSkipToPayURL() {
		return skipToPayURL;
	}
	public void setSkipToPayURL(String skipToPayURL) {
		this.skipToPayURL = skipToPayURL;
	}
	public String getMerSettleCurrency() {
		return merSettleCurrency;
	}
	public void setMerSettleCurrency(String merSettleCurrency) {
		this.merSettleCurrency = merSettleCurrency;
	}
	public String getRiskScore() {
		return riskScore;
	}
	public void setRiskScore(String riskScore) {
		this.riskScore = riskScore;
	}
	public String getIpCountry() {
		return ipCountry;
	}
	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	public String getM() {
		return m;
	}
	public void setM(String m) {
		this.m = m;
	}
	public String getCardFullName() {
		return cardFullName;
	}
	public void setCardFullName(String cardFullName) {
		this.cardFullName = cardFullName;
	}
	public String getCardFullPhone() {
		return cardFullPhone;
	}
	public void setCardFullPhone(String cardFullPhone) {
		this.cardFullPhone = cardFullPhone;
	}
	public String getExpYearStr() {
		return expYearStr;
	}
	public void setExpYearStr(String expYearStr) {
		this.expYearStr = expYearStr;
	}
	public String getExpMonthStr() {
		return expMonthStr;
	}
	public void setExpMonthStr(String expMonthStr) {
		this.expMonthStr = expMonthStr;
	}
	public String getCvvStr() {
		return cvvStr;
	}
	public void setCvvStr(String cvvStr) {
		this.cvvStr = cvvStr;
	}
	public String getGoodsString() {
		return goodsString;
	}
	public void setGoodsString(String goodsString) {
		this.goodsString = goodsString;
	}
	public String getGrphoneNumber() {
		return grphoneNumber;
	}
	public void setGrphoneNumber(String grphoneNumber) {
		this.grphoneNumber = grphoneNumber;
	}
	public String getGrPerName() {
		return grPerName;
	}
	public void setGrPerName(String grPerName) {
		this.grPerName = grPerName;
	}
	public String getCurrencyTypeT() {
		return currencyTypeT;
	}
	public void setCurrencyTypeT(String currencyTypeT) {
		this.currencyTypeT = currencyTypeT;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getCurrencyID() {
		return currencyID;
	}
	public void setCurrencyID(String currencyID) {
		this.currencyID = currencyID;
	}
	public String getRespMsg() {
		return RespMsg;
	}
	public void setRespMsg(String respMsg) {
		RespMsg = respMsg;
	}
	public String getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}
	public String getTransModel() {
		return transModel;
	}
	public void setTransModel(String transModel) {
		this.transModel = transModel;
	}
	
	public String getBankOrderID() {
		return bankOrderID;
	}
	public void setBankOrderID(String bankOrderID) {
		this.bankOrderID = bankOrderID;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getReturnURL() {
		return returnURL;
	}
	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}
	public String getSubmitURL() {
		return submitURL;
	}
	public void setSubmitURL(String submitURL) {
		this.submitURL = submitURL;
	}
	public String getPayWebSite() {
		return payWebSite;
	}
	public void setPayWebSite(String payWebSite) {
		this.payWebSite = payWebSite;
	}
	public String getPayIP() {
		return payIP;
	}
	public void setPayIP(String payIP) {
		this.payIP = payIP;
	}
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCardCountry() {
		return cardCountry;
	}
	public void setCardCountry(String cardCountry) {
		this.cardCountry = cardCountry;
	}
	public String getCardState() {
		return cardState;
	}
	public void setCardState(String cardState) {
		this.cardState = cardState;
	}
	public String getCardCity() {
		return cardCity;
	}
	public void setCardCity(String cardCity) {
		this.cardCity = cardCity;
	}
	public String getCardAddress() {
		return cardAddress;
	}
	public void setCardAddress(String cardAddress) {
		this.cardAddress = cardAddress;
	}
	public String getCardZipCode() {
		return cardZipCode;
	}
	public void setCardZipCode(String cardZipCode) {
		this.cardZipCode = cardZipCode;
	}
	public String getGrCountry() {
		return grCountry;
	}
	public void setGrCountry(String grCountry) {
		this.grCountry = grCountry;
	}
	public String getGrState() {
		return grState;
	}
	public void setGrState(String grState) {
		this.grState = grState;
	}
	public String getGrCity() {
		return grCity;
	}
	public void setGrCity(String grCity) {
		this.grCity = grCity;
	}
	public String getGrAddress() {
		return grAddress;
	}
	public void setGrAddress(String grAddress) {
		this.grAddress = grAddress;
	}
	public String getGrZipCode() {
		return grZipCode;
	}
	public void setGrZipCode(String grZipCode) {
		this.grZipCode = grZipCode;
	}
	public String getGrEmail() {
		return grEmail;
	}
	public void setGrEmail(String grEmail) {
		this.grEmail = grEmail;
	}
	public String getCardNO() {
		return cardNO;
	}
	public void setCardNO(String cardNO) {
		this.cardNO = cardNO;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getExpYear() {
		return expYear;
	}
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}
	
	public String getExpMonth() {
		return expMonth;
	}
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getMerremark() {
		return merremark;
	}
	public void setMerremark(String merremark) {
		this.merremark = merremark;
	}
	public String getMerReturnURL() {
		return merReturnURL;
	}
	public void setMerReturnURL(String merReturnURL) {
		this.merReturnURL = merReturnURL;
	}
	public String getMerMgrURL() {
		return merMgrURL;
	}
	public void setMerMgrURL(String merMgrURL) {
		this.merMgrURL = merMgrURL;
	}
	public String getWebInfo() {
		return webInfo;
	}
	public void setWebInfo(String webInfo) {
		this.webInfo = webInfo;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getHashcode() {
		return hashcode;
	}
	public void setHashcode(String hashcode) {
		this.hashcode = hashcode;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getShipName() {
		return shipName;
	}
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
	public String getShipphone() {
		return shipphone;
	}
	public void setShipphone(String shipphone) {
		this.shipphone = shipphone;
	}
	public String getCardTelephone() {
		return cardTelephone;
	}
	public void setCardTelephone(String cardTelephone) {
		this.cardTelephone = cardTelephone;
	}
	public String getRetURL() {
		return retURL;
	}
	public void setRetURL(String retURL) {
		this.retURL = retURL;
	}
	public String getCardEmail() {
		return cardEmail;
	}
	public void setCardEmail(String cardEmail) {
		this.cardEmail = cardEmail;
	}
	public String getCommodityBrand() {
		return commodityBrand;
	}
	public void setCommodityBrand(String commodityBrand) {
		this.commodityBrand = commodityBrand;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
	public String getDebitType() {
		return debitType;
	}
	public void setDebitType(String debitType) {
		this.debitType = debitType;
	}
	public String getIssuingBank() {
		return issuingBank;
	}
	public void setIssuingBank(String issuingBank) {
		this.issuingBank = issuingBank;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getPaidType() {
		return paidType;
	}
	public void setPaidType(String paidType) {
		this.paidType = paidType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOrderKey() {
		return orderKey;
	}
	public void setOrderKey(String orderKey) {
		this.orderKey = orderKey;
	}
}
package com.fhtpay.transaction.model;

/**
 * 拒付预警调查单
 * 
 * @author honghao
 * @email honghao@bringallpay.com
 * @version 创建时间：2019年4月23日 下午3:06:00
 */
public class TransDisWarningOrder {
	/**
	 * 流水号
	 */
	private String tradeNo;

	/**
	 * 卡种
	 */
	private String cardType;

	/**
	 * 银行id
	 */
	private String bankId;

	/**
	 * 银行名称
	 */
	private String bankName;

	/**
	 * 通道id
	 */
	private String currencyId;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}

}

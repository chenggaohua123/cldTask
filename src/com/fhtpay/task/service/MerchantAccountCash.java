package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.transaction.model.SettleTypeInfo;

public interface MerchantAccountCash {
	/**
	 * 查询可结算的订单号
	 * */
	public List<String> queryCanSettleTradeNos(SettleTypeInfo settleTypeInfo);

}

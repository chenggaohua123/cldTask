package com.fhtpay.task.service;

import java.util.ArrayList;
import java.util.List;

import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.SettleTypeInfo;

/**
 * 妥投结算
 * */
public class MerchantAccountCashBySigned implements MerchantAccountCash {
	 private TransactionManageDao transactionManageDao;
		
		public TransactionManageDao getTransactionManageDao() {
			return transactionManageDao;
		}

		public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
			this.transactionManageDao = transactionManageDao;
		}
	@Override
	public List<String> queryCanSettleTradeNos(SettleTypeInfo settleTypeInfo) {
		List<String> list=new ArrayList<String>();
		try {
			list=transactionManageDao.queryCanSettleTradeNosBySigned(settleTypeInfo);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return list;
	}
}

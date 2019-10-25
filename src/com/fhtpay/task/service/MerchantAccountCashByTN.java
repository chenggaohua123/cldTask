package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.SettleTypeInfo;

/**
 * T+N结算
 * */
public class MerchantAccountCashByTN implements MerchantAccountCash {
	 private TransactionManageDao transactionManageDao;
		
		public TransactionManageDao getTransactionManageDao() {
			return transactionManageDao;
		}

		public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
			this.transactionManageDao = transactionManageDao;
		}
	@Override
	public List<String> queryCanSettleTradeNos(SettleTypeInfo settleTypeInfo) {
		List<String> list=transactionManageDao.queryTradeNosForTN(settleTypeInfo);
		return list;
	}
}

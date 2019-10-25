package com.fhtpay.task.service.suspicious;

import java.util.List;

import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class ContainsSuspiciousRuleProcessServiceImpl extends
		RuleSuspiciousProcessService {

	private TransactionManageDao transactionManageDao;
	
	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	@Override
	public List<SuspiciousTransDeatailInfo> processRole(RulesInfo ruleInfo,
			SuspiciousMerTerInfo merTerInfo, String susType) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

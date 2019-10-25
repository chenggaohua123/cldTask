package com.fhtpay.task.service.suspicious;

import java.util.List;

import com.fhtpay.task.model.suspicious.ParamInfo;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class NeqRuleProcessServiceImpl extends
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
		List<SuspiciousTransDeatailInfo> transInfo = null;
		ParamInfo paramInfo=ruleInfo.getParamInfo();
		ParamInfo ruleParam = ruleInfo.getRuleParamInfo();
		if(ruleParam!=null && paramInfo != null){
			if(paramInfo.getParamName()!=null && ruleParam.getParamName()!=""){
				SuspiciousInfo paramValues = new SuspiciousInfo();
				paramValues.setMer(merTerInfo);
				paramValues.setParamValue(paramInfo.getParamName());
				paramValues.setParamRuleValue(ruleParam.getParamName());
				if("2".equals(susType)){
					transInfo = transactionManageDao.querySuspiciousNqTransInfoByMerNo(paramValues);
				}
				if("3".equals(susType)){
					transInfo = transactionManageDao.querySuspiciousNqTransInfoByMerNoAndTerNo(paramValues);
				}
			}
		}
		return transInfo;
	}
	

}

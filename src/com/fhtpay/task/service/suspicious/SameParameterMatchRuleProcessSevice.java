package com.fhtpay.task.service.suspicious;

import java.util.ArrayList;
import java.util.List;

import com.fhtpay.task.model.suspicious.ParamInfo;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;
import com.fhtpay.task.model.suspicious.TransParameterInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class SameParameterMatchRuleProcessSevice extends
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
		List<SuspiciousTransDeatailInfo> transInfo = new ArrayList<SuspiciousTransDeatailInfo>();
		ParamInfo paramInfo=ruleInfo.getParamInfo();
		ParamInfo ruleParam = ruleInfo.getRuleParamInfo();
		if(ruleParam!=null && paramInfo != null &&ruleParam.getParamName()!= null &&paramInfo.getParamName()!= null){
			int paramType=0;
			String mainParameter=paramInfo.getParamName().split(",")[0];
			String param2="";
			try {
				param2=paramInfo.getParamName().split(",")[1];
			} catch (Exception e) {
			}
			//查询商户当天交易
			List<TransParameterInfo> tpi=transactionManageDao.queryTransParameterInfo(merTerInfo,paramInfo.getParamName(),mainParameter,param2);
			for(TransParameterInfo info:tpi){
				int count=0;
				if(info.getCardAddress() == null || info.getCardAddress().length()<=3){
					info.setCardAddress("");
				}else{
					count++;
				}
				if(info.getGrAddress() == null || info.getGrAddress().length()<=3){
					info.setGrAddress("");
				}else{
					count++;
				}
				if(info.getCheckToNo() == null || info.getCheckToNo() .length()<=3){
					info.setCheckToNo("");
				}else{
					count++;
				}
				if(info.getEmail() == null || info.getEmail().length()<=3){
					info.setEmail("");
				}else{
					count++;
				}
				if(info.getIPAddress() == null || info.getIPAddress().length()<=3){
					info.setIPAddress("");
				}else{
					count++;
				}
				List<SuspiciousTransDeatailInfo> temp = null ;
				if(ruleParam.getParamName().startsWith("refund")){
					paramType=1;
					temp=transactionManageDao.querySameParameterMatchRefundDis(ruleParam,info,count,paramType,mainParameter);
				}else if(ruleParam.getParamName().startsWith("dishonor")){
					paramType=2;
					temp=transactionManageDao.querySameParameterMatchRefundDis(ruleParam,info,count,paramType,mainParameter);
				}else if(ruleParam.getParamName().startsWith("differentSuccess")){
					String matchParameter="";
					try {
						matchParameter=paramInfo.getParamName().split(",")[1];
						paramType=3;
						temp=transactionManageDao.querySameParameterMatchDifferentSuccess(ruleParam,info,count,paramType,mainParameter,matchParameter);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else if(ruleParam.getParamName().startsWith("differentRefund")){
					String matchParameter="";
					try {
						matchParameter=paramInfo.getParamName().split(",")[1];
						paramType=4;
						temp=transactionManageDao.querySameParameterMatchDifferentRefundDis(ruleParam, info, count,paramType,mainParameter,matchParameter);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else if(ruleParam.getParamName().startsWith("differentDishonor")){
					String matchParameter="";
					try {
						matchParameter=paramInfo.getParamName().split(",")[1];
						paramType=5;
						temp=transactionManageDao.querySameParameterMatchDifferentRefundDis(ruleParam, info, count,paramType,mainParameter,matchParameter);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if(temp != null && temp.size()>0){
					transInfo.addAll(temp);
				}
			}
		}
		return transInfo;
	}
	

}

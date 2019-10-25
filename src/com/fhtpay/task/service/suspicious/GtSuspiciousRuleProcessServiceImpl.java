package com.fhtpay.task.service.suspicious;

import java.util.List;
import java.util.Map;

import com.fhtpay.task.model.suspicious.ParamInfo;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;
import com.fhtpay.task.model.suspicious.SuspiciousValueInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class GtSuspiciousRuleProcessServiceImpl extends
		RuleSuspiciousProcessService {

	private TransactionManageDao transactionManageDao;
	
	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	@Override
	public List<SuspiciousTransDeatailInfo> processRole(RulesInfo ruleInfo, SuspiciousMerTerInfo merTerInfo, String susType) {
		List<SuspiciousTransDeatailInfo> transInfo = null;
		ParamInfo ruleParam = ruleInfo.getRuleParamInfo();
		if(ruleParam!=null){
			String value = "";
			SuspiciousInfo paramValues = new SuspiciousInfo();
			System.out.println(susType);
			Map<String, String> tableValue = ruleParam.getTableValue();
			SuspiciousValueInfo valuesInfo = new SuspiciousValueInfo();
			if(tableValue!=null && tableValue.size()>0){
				valuesInfo.setColKeyName(ruleParam.getColKeyName());
				valuesInfo.setColKeyValue(tableValue.get(ruleParam.getColKeyName()));
				valuesInfo.setColValueName(ruleParam.getColValueName());
				valuesInfo.setColValue(tableValue.get(ruleParam.getColValueName()));
			}
			paramValues.setMer(merTerInfo);
			paramValues.setValues(valuesInfo);
//			System.out.println(ruleParam.getTableName());
			if(ruleParam.getTableName()!=null && ruleParam.getTableName()!=""){
				if(ruleParam.getTableName().toLowerCase().contains("iplimit")){//同一IP支付次数限制
					value = "IPLimit";
					paramValues.setTableName(value);
				}
				if(ruleParam.getTableName().toLowerCase().contains("emaillimit")){//同一邮箱支付次数限制
					value = "emailLimit";
					paramValues.setTableName(value);
				}
				if(ruleParam.getTableName().toLowerCase().contains("cardnolimit")){//同一卡号支付次数限制
					value = "cardNoLimit";
					paramValues.setTableName(value);
				}
				if(ruleParam.getTableName().toLowerCase().contains("graddresslimit")){//同一收货地址支付次数限制
					value = "grAddressLimit";
					paramValues.setTableName(value);
				}
				if(ruleParam.getTableName().toLowerCase().contains("cardfullphonelimit")){//同一账单电话支付次数限制
					value = "cradFullPhoneLimit";
					paramValues.setTableName(value);
				}
				if(ruleParam.getTableName().toLowerCase().contains("cardfullnamelimit")){//同一账单姓名支付次数限制
					value = "cardFullNameLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountlimit"))){//单笔支付金额限制
					value = "amountLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountiplimit"))){//同一IP支付金额限制
					value = "amountIpLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountcardnolimit"))){//同一卡号支付金额限制
					value = "amountCardNoLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountemaillimit"))){//同一邮箱支付金额限制
					value = "amountEmailLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountcardphonelimit"))){//同一账单电话支付金额限制
					value = "amountCardFullPhoneLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountcardfullnamelimit"))){//同一账单用户支付金额限制
					value = "amountCardFullNameLimit";
					paramValues.setTableName(value);
				}
				if((ruleParam.getTableName().toLowerCase().contains("amountgraddresslimit"))){//同一收货地址支付金额限制
					value = "amountGrAddressLimit";
					paramValues.setTableName(value);
				}
				if(paramValues.getTableName()!=null && paramValues.getTableName()!=""){
					if("2".equals(susType)){
						transInfo = transactionManageDao.querySuspiciousLtTransInfoByMerNo(paramValues);
					}
					if("3".equals(susType)){
						transInfo = transactionManageDao.querySuspiciousLtTransInfoByMerNoAndTerNo(paramValues);
					}
				}
			}
		}
		return transInfo;
	}
	

}

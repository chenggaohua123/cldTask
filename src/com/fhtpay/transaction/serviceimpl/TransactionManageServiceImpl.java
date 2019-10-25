package com.fhtpay.transaction.serviceimpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fhtpay.common.Constants;
import com.fhtpay.common.JSONPParserUtil;
import com.fhtpay.common.PaymentConfig;
import com.fhtpay.common.Tools;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.CheckToNoInfo;
import com.fhtpay.task.model.PoolSettleInfo;
import com.fhtpay.task.model.ThreatMetrixResultInfo;
import com.fhtpay.task.model.TransInfoForExcel;
import com.fhtpay.task.model.callback.TransCallbackInfo;
import com.fhtpay.task.model.refundReturn.RefundConfigInfo;
import com.fhtpay.task.model.refundReturn.RefundTransInfo;
import com.fhtpay.task.model.suspicious.ParamInfo;
import com.fhtpay.task.model.suspicious.ParamValueInfo;
import com.fhtpay.task.model.suspicious.RuleProcessClass;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTransInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransOrderInfo;
import com.fhtpay.task.model.warn.TransWarnEmailInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.model.website.MerchantWebSiteInfo;
import com.fhtpay.task.service.suspicious.RuleSuspiciousProcessService;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.BankSourceInfo;
import com.fhtpay.transaction.model.BankSourceRateInfo;
import com.fhtpay.transaction.model.Complaint;
import com.fhtpay.transaction.model.DisFineCount;
import com.fhtpay.transaction.model.DisFineStepInfo;
import com.fhtpay.transaction.model.DuplicateTransCount;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.ExceptionSettleInfo;
import com.fhtpay.transaction.model.ExchangeRateInfo;
import com.fhtpay.transaction.model.IppTrackInfo;
import com.fhtpay.transaction.model.MerchantAccount;
import com.fhtpay.transaction.model.MerchantAccountAccess;
import com.fhtpay.transaction.model.MerchantAccountAccessDetail;
import com.fhtpay.transaction.model.PaymentInfo;
import com.fhtpay.transaction.model.SettleTypeInfo;
import com.fhtpay.transaction.model.TransCheckedInfo;
import com.fhtpay.transaction.model.TransDisWarningOrder;
import com.fhtpay.transaction.service.TransactionManageService;

public class TransactionManageServiceImpl implements TransactionManageService{
	
	private static Log logger = LogFactory.getLog(TransactionManageServiceImpl.class);
	
    private TransactionManageDao transactionManageDao;
	
	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}
	
	@Override
	public List<TransCheckedInfo> queryTransCheckedInfo() {
		return transactionManageDao.queryTransCheckedInfo();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateTransChecked(List<TransCheckedInfo> list) {
		int count=0;
		for(TransCheckedInfo transCheckedInfo:list){
			int i=transactionManageDao.updateTransInfo(transCheckedInfo);
			if(i==0){//勾兑失败将勾兑状态更新为勾兑异常
				transactionManageDao.updateTransInfoExpByTradeNo(transCheckedInfo.getTradeNo());
				transactionManageDao.updateTransCheckedInfoExpByTradeNo(transCheckedInfo.getTradeNo());
			}else{//勾兑成功 将勾兑状态更新为已勾兑
				transactionManageDao.updateTransCheckedInfoSuccessByTradeNo(transCheckedInfo.getTradeNo());
			}
			count+=i;
		}
		return count;
	}
	
	@Override
	public List<MerchantAccount> queryNoAccountMerchantInfo() {
		return transactionManageDao.queryNoAccountMerchantInfo();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertMerchantAccount(List<MerchantAccount> list) {
		int count=0;
		for(MerchantAccount merchantAccount:list){
			count+=transactionManageDao.insertMerchantAccount(merchantAccount);
		}
		return count;
	}
	@Override
	public List<String> queryTradeNoAccess() {
		return transactionManageDao.queryTradeNoAccess();
	}
	@Override
	public List<String> queryDJTradeNoAccess() {
		return transactionManageDao.queryDJTradeNoAccess();
	}
	@Override
	public List<String> queryJDTradeNoAccess() {
		return transactionManageDao.queryJDTradeNoAccess();
	}
	@Override
	public List<String> queryTKTradeNoAccess() {
		return transactionManageDao.queryTKTradeNoAccess();
	}
	@Override
	public List<String> queryJFTradeNoAccess() {
		return transactionManageDao.queryJFTradeNoAccess();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateMerchantAccountTotalAmount(List<String> list,String cashType) {
		//1.查询未入账的订单
		List<MerchantAccountAccess> maas=transactionManageDao.queryMerchantAccountTotalAmount(list,cashType);
		//2.更新交易表入账状态access=1 已入账
		int count=transactionManageDao.updateTransAccess(list);
		if("0".equals(cashType)){//【字典】-【CASHTYPE】=0系统入账
			String accessId=Tools.getAccessId();
			for(String tradeNo:list){
				MerchantAccountAccessDetail merchantAccountAccessDetail=new MerchantAccountAccessDetail();
				merchantAccountAccessDetail.setTradeNo(tradeNo);
				merchantAccountAccessDetail.setAccessId(accessId);
				transactionManageDao.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
			}
			for(MerchantAccountAccess maa:maas){
				maa.setId(accessId);
				maa.setCashType("-5");//【字典】-【CASHTYPE】=-5交易账户总金额入账
				maa.setAccountType("0");
				MerchantAccount merchantAccount=new MerchantAccount();
				maa.setAmount(maa.getTotalAmount());
				transactionManageDao.insertMerchantAccountAccess(maa);
				merchantAccount.setId(maa.getAccountID());
				merchantAccount.setTotalAmount(maa.getTotalAmount());
				transactionManageDao.updateMerchantAccount(merchantAccount);
			}
			accessId=Tools.getAccessId();
			for(String tradeNo:list){
				MerchantAccountAccessDetail merchantAccountAccessDetail=new MerchantAccountAccessDetail();
				merchantAccountAccessDetail.setTradeNo(tradeNo);
				merchantAccountAccessDetail.setAccessId(accessId);
				transactionManageDao.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
			}
			for(MerchantAccountAccess maa:maas){
				maa.setId(accessId);
				maa.setCashType("-7");//【字典】-【CASHTYPE】=-7保证金账户总金额入账
				maa.setAccountType("1");
				MerchantAccount merchantAccount=new MerchantAccount();
				maa.setAmount(maa.getBondAmount());
				transactionManageDao.insertMerchantAccountAccess(maa);
				merchantAccount.setId(maa.getAccountID());
				merchantAccount.setBondAmount(maa.getBondAmount());
				transactionManageDao.updateMerchantAccount(merchantAccount);
			}
		}else{//异常单入账 和保证金无关
			String accessId=Tools.getAccessId();
			for(String tradeNo:list){
				MerchantAccountAccessDetail merchantAccountAccessDetail=new MerchantAccountAccessDetail();
				merchantAccountAccessDetail.setTradeNo(tradeNo);
				merchantAccountAccessDetail.setAccessId(accessId);
				transactionManageDao.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
			}
			for(MerchantAccountAccess maa:maas){
				maa.setAccountType("0");
				maa.setId(accessId);
				maa.setCashType(cashType);
				MerchantAccount merchantAccount=new MerchantAccount();
				maa.setAmount(maa.getTotalAmount());
				if("-1".equals(cashType)||"-2".equals(cashType)){//【字典】-【CASHTYPE】=-1交易冻结  -2交易解冻
					merchantAccount.setTransFrozenAmount(maa.getAmount()*-1);
				}else if("-3".equals(cashType)){/***退款交易入账**/
					double tempRefundTotalAmount=maa.getTotalAmount()+maa.getRefundFee()+maa.getRefundTransFee();
					//处理是否免除手续费问题
					//查询免除多少手续费
					if(maa.getRefundTransFee()>0){
						maa.setCashType("-14");//【字典】-【CASHTYPE】=-14退款交易手续费返还
						maa.setAmount(maa.getRefundTransFee());
						transactionManageDao.insertMerchantAccountAccess(maa);
					}
					//处理收取退款处理费问题
					//查询收取多少退款处理费
					if(maa.getRefundFee()<0){
						maa.setCashType("-13");//【字典】-【CASHTYPE】=-13退款手续费
						maa.setAmount(maa.getRefundFee());
						transactionManageDao.insertMerchantAccountAccess(maa);
					}
					
					//设置回退款总金额
					maa.setCashType("-3");//【字典】-【CASHTYPE】=-3交易退款
					maa.setAmount(maa.getTotalAmount());
					maa.setTotalAmount(tempRefundTotalAmount);
				}else if("-4".equals(cashType)){/***拒付交易入账**/ //【字典】-【CASHTYPE】=-4交易拒付
					double tempDisTotalAmount=maa.getTotalAmount()+maa.getDisFee();//商户结算金额合计+拒付处理费合计
					//处理是收取拒付手续费问题
					//查询收取多少拒付手续费
					if(maa.getDisFee()<0){
						maa.setCashType("-15");//【字典】-【CASHTYPE】=-15拒付手续费
						maa.setAmount(maa.getDisFee());
						transactionManageDao.insertMerchantAccountAccess(maa);
					}
					
					//设置回拒付总金额
					maa.setCashType("-4");//【字典】-【CASHTYPE】=-4交易拒付
					maa.setAmount(maa.getTotalAmount());
					maa.setTotalAmount(tempDisTotalAmount);
				}
				
				transactionManageDao.insertMerchantAccountAccess(maa);
				merchantAccount.setId(maa.getAccountID());
				merchantAccount.setCashAmount(maa.getTotalAmount());
				merchantAccount.setTotalAmount(maa.getTotalAmount());
				transactionManageDao.updateMerchantAccount(merchantAccount);
			}
		}
		
		return count;
	}
	@Override
	public List<SettleTypeInfo> querySettleTypeInfo() {
		return transactionManageDao.querySettleTypeInfo();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateMerchantAccountCashAmount(List<String> tradeNos, SettleTypeInfo settleTypeInfo) {
		int count=0;
		String batchNo=Tools.getBatchNo();
		String accessId=Tools.getAccessId();
		MerchantAccountAccess maa=transactionManageDao.queryMerchantAccountCash(tradeNos);
		count=transactionManageDao.updateTransBatchNo(tradeNos,batchNo);
		for(String tradeNo:tradeNos){
			MerchantAccountAccessDetail merchantAccountAccessDetail=new MerchantAccountAccessDetail();
			merchantAccountAccessDetail.setTradeNo(tradeNo);
			merchantAccountAccessDetail.setAccessId(accessId);
			merchantAccountAccessDetail.setBatchNo(batchNo);
			transactionManageDao.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
		}
		maa.setId(accessId);
		maa.setAmount(maa.getCashAmount());
		maa.setCashType("-6");//交易提现入账
		maa.setAccountType("0");
		transactionManageDao.insertMerchantAccountAccess(maa);
		MerchantAccount merchantAccount=new MerchantAccount();
		merchantAccount.setId(maa.getAccountID());
		merchantAccount.setCashAmount(maa.getCashAmount());
		transactionManageDao.updateMerchantAccount(merchantAccount);
		return count;
	}
	@Override
	public List<String> queryCanSettleBondTradeNos(SettleTypeInfo settleTypeInfo) {
		return transactionManageDao.queryCanSettleBondTradeNos(settleTypeInfo);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateMerchantAccountBondCashAmount(List<String> tradeNos,
			SettleTypeInfo settleTypeInfo) {
		int count=0;
		String bondBatchNo=Tools.getBondBatchNo();
		String accessId=Tools.getAccessId();
		MerchantAccountAccess maa=transactionManageDao.queryMerchantAccountBondCash(tradeNos);
		count=transactionManageDao.updateTransBondBatchNo(tradeNos,bondBatchNo);
		for(String tradeNo:tradeNos){
			MerchantAccountAccessDetail merchantAccountAccessDetail=new MerchantAccountAccessDetail();
			merchantAccountAccessDetail.setTradeNo(tradeNo);
			merchantAccountAccessDetail.setAccessId(accessId);
			transactionManageDao.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
		}
		maa.setId(accessId);
		maa.setCashType("-8");//保证金账户提现入账
		maa.setAccountType("1");
		maa.setAmount(maa.getBondCashAmount());
		transactionManageDao.insertMerchantAccountAccess(maa);
		MerchantAccount merchantAccount=new MerchantAccount();
		merchantAccount.setId(maa.getAccountID());
		merchantAccount.setBondCashAmount(maa.getBondCashAmount());
		transactionManageDao.updateMerchantAccount(merchantAccount);
		return count;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int insertSourceBCRateInfo(List<String> arr) {
		int count=0;
		List<String> list=new ArrayList<String>();
		List<String> name=new ArrayList<String>();
		//清空以前的中行汇率数据记录
		transactionManageDao.deleteBankSourceRateInfo();
		int i=0;
		for (String str : arr) {
			if (str.matches("[a-zA-Z]{3}")) {
				if(name.size()==7&&list.size()==7){
					count+=transactionManageDao.insertBankSourceInfo(list);
					list.clear();
					i=0;
				}
			}
			if(name.size()<7){
				name.add(str);
			}else{
				list.add(name.get(i)+","+str);
				i++;
			}
		}
		count+=transactionManageDao.insertBankSourceInfo(list);
		List<BankSourceInfo> bankSourceInfos=transactionManageDao.queryBankSourceRateInfo();
		transactionManageDao.insertBankSourceRateInfoLog();
		for(BankSourceInfo info:bankSourceInfos){
			BankSourceRateInfo bankSourceRateInfo=new BankSourceRateInfo();
			String currencyName=info.getParam1().split(",")[1];
			bankSourceRateInfo.setSourceCurrencyCode(currencyName);
			bankSourceRateInfo.setTargetCurrencyCode("CNY");
			bankSourceRateInfo.setBcDate(info.getParam7().split(",",2)[1]);
			bankSourceRateInfo.setStatus(0);
			
			bankSourceRateInfo.setRateType(info.getParam2().split(",")[0]);
			bankSourceRateInfo.setRate(new BigDecimal(info.getParam2().split(",")[1]).divide(new BigDecimal(100)));
			if(transactionManageDao.countExistsRate(bankSourceRateInfo)==0){
				transactionManageDao.insertBankSourceRateInfo(bankSourceRateInfo);
			}else{
				transactionManageDao.updateBankSourceRateInfo(bankSourceRateInfo);
			}
			
			bankSourceRateInfo.setRateType(info.getParam3().split(",")[0]);
			bankSourceRateInfo.setRate(new BigDecimal(info.getParam3().split(",")[1]).divide(new BigDecimal(100)));
			if(transactionManageDao.countExistsRate(bankSourceRateInfo)==0){
				transactionManageDao.insertBankSourceRateInfo(bankSourceRateInfo);
			}else{
				transactionManageDao.updateBankSourceRateInfo(bankSourceRateInfo);
			}
			
			bankSourceRateInfo.setRateType(info.getParam4().split(",")[0]);
			bankSourceRateInfo.setRate(new BigDecimal(info.getParam4().split(",")[1]).divide(new BigDecimal(100)));
			if(transactionManageDao.countExistsRate(bankSourceRateInfo)==0){
				transactionManageDao.insertBankSourceRateInfo(bankSourceRateInfo);
			}else{
				transactionManageDao.updateBankSourceRateInfo(bankSourceRateInfo);
			}
			
			bankSourceRateInfo.setRateType(info.getParam5().split(",")[0]);
			bankSourceRateInfo.setRate(new BigDecimal(info.getParam5().split(",")[1]).divide(new BigDecimal(100)));
			if(transactionManageDao.countExistsRate(bankSourceRateInfo)==0){
				transactionManageDao.insertBankSourceRateInfo(bankSourceRateInfo);
			}else{
				transactionManageDao.updateBankSourceRateInfo(bankSourceRateInfo);
			}
			
			bankSourceRateInfo.setRateType(info.getParam6().split(",")[0]);
			bankSourceRateInfo.setRate(new BigDecimal(info.getParam6().split(",")[1]).divide(new BigDecimal(100)));
			if(transactionManageDao.countExistsRate(bankSourceRateInfo)==0){
				transactionManageDao.insertBankSourceRateInfo(bankSourceRateInfo);
			}else{
				transactionManageDao.updateBankSourceRateInfo(bankSourceRateInfo);
			}
		}
		/**
		 * 更新汇率表
		 * */
		try {
			
			List<ExchangeRateInfo> infos=transactionManageDao.queryExchangRateInfo();
			for(ExchangeRateInfo info:infos){
				
				BigDecimal rate;
				if("CNY".equalsIgnoreCase(info.getSourceCurrency())&&!"CNY".equals(info.getTargetCurrency())){
					rate=transactionManageDao.queryBankRateCNY(info);
				}else{
					rate =transactionManageDao.queryBankRate(info);
				}
				
				if(rate.doubleValue()>0){//异常汇率
					System.out.println("正常汇率:"+info.getSourceCurrency()+" "+info.getTargetCurrency() +":"+rate);
					info.setRate(rate);
					transactionManageDao.updateExchagRateInfo(info);
				}else{//正常汇率
					System.out.println("异常汇率:"+info.getSourceCurrency()+" "+info.getTargetCurrency() +":"+rate);
					transactionManageDao.updateExceptionExchagRateInfo(info);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public List<EmailSendInfo> queryEmailSendInfo() {
		return transactionManageDao.queryEmailSendInfo();
	}
	
	@Override
	public int updateEmailSendInfo(EmailSendInfo info) {
		return transactionManageDao.updateEmailSendInfo(info);
	}
	
	@Override
	public int updateEmailCount() {
		return transactionManageDao.updateEmailCount();
	}
	
	@Override
	public List<DuplicateTransCount> queryNoFlagDate() {
		List<DuplicateTransCount> list=null;
		try {
			list=transactionManageDao.queryNoFlagDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<String> queryNoFlagTradeNos(String doDate) {
		List<String> list=null;
		try {
			list=transactionManageDao.queryNoFlagTradeNos(doDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int updateTransInfoDupplicateFlag(String tradeNo,String doDate) {
		try{
		//判断当前单已成功重复了多少次 重复失败了多少次
		DuplicateTransCount dtc=transactionManageDao.queryDuplicateTransCount(tradeNo,doDate);
		//如果成功重复数量大于1 那么标记当前订单为不重复订单
		if("00".equals(dtc.getRespCode())){//当前订单成功
			//标记当前订单不重复
			transactionManageDao.updateTransInfoDupplicateFlag(tradeNo,2);
			//成功重复的订单为0 失败重复的订单不为0的时候
			if(dtc.getSuccessDuplicate()==0&&dtc.getFailDuplicate()!=0){
				//更新之前失败订单的中第一笔不重复的为重复
				//查询前面失败订单的中第一笔不重复的为重复
				List<String> tradeNos=transactionManageDao.queryBeforeFailTransInfoDuplicateFlag(doDate,tradeNo);
				for(String  str:tradeNos){
					transactionManageDao.updateTransInfoDupplicateFlag(str,1);
				}
			}
			return 1;
		}
		if("01".equals(dtc.getRespCode())){//当期订单失败
			//如果当前单之前没有重复的订单
			if(dtc.getSuccessDuplicate()==0&&dtc.getFailDuplicate()==0){
				transactionManageDao.updateTransInfoDupplicateFlag(tradeNo,2);
			}else{
				transactionManageDao.updateTransInfoDupplicateFlag(tradeNo,1);
			}
			
			return 1;
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForTransFail() {
		return transactionManageDao.queryExceptionSettleInfoForTransFail();
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateChargeForTransFailOrder(ExceptionSettleInfo info) {
		//通过设置条件查询符合要求的订单
		List<String> tradeNos=transactionManageDao.queryConfirmTransFailOrder(info);
		int count = 0;
		if (tradeNos != null && tradeNos.size() > 0) {
			String accessId = Tools.getAccessId();
			// 只得到出入账明细 不做计算
			MerchantAccountAccess maa = transactionManageDao
					.queryMerchantAccountBondCash(tradeNos);
			for (String tradeNo : tradeNos) {
				MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
				merchantAccountAccessDetail.setTradeNo(tradeNo);
				merchantAccountAccessDetail.setBatchNo(accessId);
				merchantAccountAccessDetail.setAccessId(accessId);
				count+=transactionManageDao
						.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
			}
			maa.setId(accessId);
			maa.setCurrency(info.getCurrency());

			maa.setAmount(info.getAmount().doubleValue() * tradeNos.size() * -1);
			maa.setCashType("-12");// 交易提现入账
			maa.setAccountType("0");
			transactionManageDao.insertMerchantAccountAccess(maa);
			MerchantAccount merchantAccount = new MerchantAccount();
			merchantAccount.setId(maa.getAccountID());
			merchantAccount.setCashAmount(maa.getAmount());
			merchantAccount.setTotalAmount(maa.getAmount());
			transactionManageDao.updateMerchantAccount(merchantAccount);
		}
		return count;
	}
	
	@Override
	public String getShaKeyByMerNoAndTerNo(String merNo, String terNo) {
		return transactionManageDao.getShaKeyByMerNoAndTerNo(merNo, terNo);
	}
	@Override
	public List<PaymentInfo> queryMustBackTradeNos() {
		return transactionManageDao.queryMustBackTradeNos();
	}
	
	@Override
	public int updateMustBackTradeNoFlag(List<PaymentInfo> list) {
		return transactionManageDao.updateMustBackTradeNoFlag(list);
	}
	
	
	@Override
	public List<RulesInfo> queryRuleInfoByMerNo(String merNo){
		List<RulesInfo> list = transactionManageDao.queryRuleInfoByMerNo(merNo);
		for(RulesInfo info:list){
			//查询参数
			ParamInfo paramInfo = this.queryParamInfoByParamId(info.getParamId());
			ParamInfo ruleParamInfo = this.queryParamInfoByParamId(info.getRuleParamValueId());
			//查询处理类
			RuleProcessClass processClass = this.queryRuleProcessClassByClassId(info.getProcessClassId());
			info.setParamInfo(paramInfo);
			info.setRuleParamInfo(ruleParamInfo);
			info.setProcessClass(processClass);
		}
		return list;
	}
	
	@Override
	public List<RulesInfo> queryRuleInfoByMerNoAndTerNo(String merNo,
			String terNo) {
		List<RulesInfo> list = transactionManageDao.queryRuleInfoByMerNoAndTerNo(merNo, terNo);
		for(RulesInfo info:list){
			//查询参数
			ParamInfo paramInfo = this.queryParamInfoByParamId(info.getParamId());
			ParamInfo ruleParamInfo = this.queryParamInfoByParamId(info.getRuleParamValueId());
			//查询处理类
			RuleProcessClass processClass = this.queryRuleProcessClassByClassId(info.getProcessClassId());
			info.setParamInfo(paramInfo);
			info.setRuleParamInfo(ruleParamInfo);
			info.setProcessClass(processClass);
		}
		return list;
	}
	
	/**
	 * 查找参数值列表信息
	 */
	public ParamInfo queryParamInfoByParamId(String paramId) {
		ParamInfo info = transactionManageDao.queryParamInfoByParamId(paramId);
		if(null == info){
			return null;
		}else{
			//查询参数列表值
			List<ParamValueInfo> values = transactionManageDao.queryParamValuesInfoByParamId(paramId);
			info.setParamValuesList(values);
			String paramType = info.getType();
			if("String".equals(paramType)){
				List<String> list = transactionManageDao.queryParamListValuesByParamId(paramId);
				if(list.size() >0){
					info.setStringValue(list.get(0));
					info.setListValue(list);
				}
			}else if("List".equals(paramType)){
				List<String> list = transactionManageDao.queryParamListValuesByParamId(paramId);
				if(list.size() >0){
					info.setStringValue(list.get(0));
					info.setListValue(list);
				}
			}else if("Table".equals(paramType)){
				if(values.size()>0){
					ParamValueInfo value = values.get(0);
					String tableName = value.getTableName();
					String colKey = value.getColKey();
					String colValue = value.getColValue();
					if(null != tableName && null != colKey && null != colValue && 
							tableName.trim().length() > 0 && colKey.trim().length()>0 && colValue.trim().length()>0){
						List<Map<String, String>> tableValueList = transactionManageDao.queryParamTableValues(tableName, colKey, colValue);
						Map<String, String> tableValue = new HashMap<String, String>();
						for(Map<String,String> tempMap:tableValueList){
							for(int i = 0 ; i < tempMap.size();i++){
								tableValue.put(tempMap.keySet().toArray()[i].toString(), tempMap.get(tempMap.keySet().toArray()[i]));
//								System.out.println(tempMap.keySet().toArray()[i].toString()+"---"+tempMap.get(tempMap.keySet().toArray()[i]));
							}
						}
						info.setTableValue(tableValue);
						info.setTableName(tableName);
						info.setColKeyName(colKey);
						info.setColValueName(colValue);
						info.setColKeyValue(tableValue.get(colKey));
						info.setColValue(tableValue.get(colValue));
					}
				}
			}else{
				return null;
			}
		}
		return info;
	}
	/**
	 * 查找处理方法类信息
	 */
	public RuleProcessClass queryRuleProcessClassByClassId(String classId) {
		return transactionManageDao.queryRuleProcessClassByClassId(classId);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public String startSuspiciousProcessClass(List<RulesInfo> list,
			SuspiciousMerTerInfo merTerInfo, String susType) {
		int a = 0;
		if (list == null || !(list.size() > 0)) {
			return "规则信息为空";
		}
		for (RulesInfo rule : list) {
			RuleProcessClass processClass = rule.getProcessClass();
			if (processClass == null) {
				System.out.println("没有代理类");
				continue;
			}
			RuleSuspiciousProcessService processService = (RuleSuspiciousProcessService) TaskMain
					.getInstance().getBeanFactory()
					.getBean(processClass.getClassName());
			List<SuspiciousTransDeatailInfo> transList = processService
					.processRole(rule, merTerInfo, susType);
			if (transList == null || !(transList.size() > 0)) {
				System.out.println("没有保存信息");
				continue;
			}
			SuspiciousMerTransInfo merTrans = new SuspiciousMerTransInfo();
			if ("2".equals(susType)) {
				merTrans.setMerNo(merTerInfo.getMerNo());
			}
			if ("3".equals(susType)) {
				merTrans.setMerNo(merTerInfo.getMerNo());
				merTrans.setTerNo(merTerInfo.getTerNo());
			}
			merTrans.setSusType(susType);
			merTrans.setRuleId(Integer.parseInt(rule.getRuleId()));
			try {
				a = transactionManageDao.saveSuspiciousMerTransInfo(merTrans);
				SuspiciousTransOrderInfo transOrder = new SuspiciousTransOrderInfo();
				transOrder.setSyspId(merTrans.getId());
				for (SuspiciousTransDeatailInfo trans : transList) {
					if (trans.getInfo() != null && trans.getInfo() != "") {
						transOrder.setInfo(trans.getInfo());
						if (trans.getTradeNoList() == null
								|| "".equals(trans.getTradeNoList())) {
							System.out.println("流水号列表为空");
							continue;
						}
						String[] tradeNos = trans.getTradeNoList().split(",");
						if (tradeNos == null || !(tradeNos.length > 0)) {
							System.out.println("流水号为空");
							continue;
						}
						for (String tradeNo : tradeNos) {
							transOrder.setTradeNo(tradeNo);
							a = transactionManageDao
									.saveSuspiciousTransOrderInfo(transOrder);
						}
					}
				}
			if (a > 0) {
				return "保存成功";
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
		return "保存失败";
	}
	
	@Override
	public List<SuspiciousMerInfo> querySuspiciousMerInfo() {
		return transactionManageDao.querySuspiciousMerInfo();
	}
	
	@Override
	public List<SuspiciousMerTerInfo> querySuspiciousMerTerInfo() {
		return transactionManageDao.querySuspiciousMerTerInfo();
	}
	@Override
	public List<String> queryNeedChangeTransactionTradeNos() {
		return transactionManageDao.queryNeedChangeTransactionTradeNos();
	}
	
	@Override
	public List<TransWarnInfo> queryTransWarnInfo() {
		return transactionManageDao.queryTransWarnInfo();
	}

	@Override
	public List<TransWarnEmailInfo> queryTransWarnEmailInfo(TransWarnInfo info) {
		return transactionManageDao.queryTransWarnEmailInfo(info);
	}
	@Override
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForDisFine() {
		return transactionManageDao.queryExceptionSettleInfoForDisFine();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateMerchantAccountForDisFine(ExceptionSettleInfo info) {
		int count = 0;
		
		if(info.getIsAllOrOver()==2){//拒付阶梯手续费
			// 通过设置条件查询符合要求的订单
			//查询商户是否复核拒付罚金收取
			//计算商户上个月的拒付率
			DisFineCount dc=transactionManageDao.queryMerchantDisRateByMerNoAndTerNo(info);
			DisFineStepInfo ds=transactionManageDao.queryDisFineStepInfoById(info.getStepId());
			
			//计算收取多少笔
			int limitCount=0;
			//计算从那一笔后开始收取
			int startLimit=0;
			//阶梯一
			if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>=ds.getStart1()/100
					
					) {
				if(ds.getEnd1()==-1 || ds.getEnd1()/100>=dc.getDisCount()*1.0/dc.getSuccessCount()){//全部收取
					limitCount=dc.getDisCount();
				}else{//区间部分收取
					try {
						limitCount=(int)Math.floor(ds.getEnd1()/100*dc.getSuccessCount());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				info.setStartLimit(startLimit);
				info.setLimitCount(limitCount);
				if(limitCount>0&&ds.getStepAmount1()>0){
					String accessId = Tools.getAccessId();
					// 只得到出入账明细 不做计算
					MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
					List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
					for (String tradeNo : tradeNos) {
						MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
						merchantAccountAccessDetail.setTradeNo(tradeNo);
						merchantAccountAccessDetail.setBatchNo(accessId);
						merchantAccountAccessDetail.setAccessId(accessId);
						merchantAccountAccessDetail.setAmount(ds.getStepAmount1());
						count+=transactionManageDao
								.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
					}
					maa.setId(accessId);
					maa.setCurrency(info.getCurrency());
					
					maa.setAmount(ds.getStepAmount1()*info.getLimitCount()* -1);
					maa.setCashType("-11");// 交易提现入账
					maa.setAccountType("0");
					transactionManageDao.insertMerchantAccountAccess(maa);
					MerchantAccount merchantAccount = new MerchantAccount();
					merchantAccount.setId(maa.getAccountID());
					merchantAccount.setCashAmount(maa.getAmount());
					merchantAccount.setTotalAmount(maa.getAmount());
					transactionManageDao.updateMerchantAccount(merchantAccount);
				}
			}
			//阶梯二
			if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>=ds.getStart2()/100
					
					&&ds.getStart2()>0
					) {
				
				//开始从哪一笔后开始收取
				startLimit+=limitCount;
				if(ds.getEnd2()==-1 || ds.getEnd2()/100>=dc.getDisCount()*1.0/dc.getSuccessCount()){//全部收取
					limitCount=dc.getDisCount()-startLimit;
				}else{//区间部分收取
					try {
						limitCount=(int)Math.floor(ds.getEnd2()/100*dc.getSuccessCount())-startLimit;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				info.setLimitCount(limitCount);
				info.setStartLimit(startLimit);
				if(limitCount>0&&ds.getStepAmount2()>0){
					String accessId = Tools.getAccessId();
					// 只得到出入账明细 不做计算
					MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
					List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
					for (String tradeNo : tradeNos) {
						MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
						merchantAccountAccessDetail.setTradeNo(tradeNo);
						merchantAccountAccessDetail.setBatchNo(accessId);
						merchantAccountAccessDetail.setAccessId(accessId);
						merchantAccountAccessDetail.setAmount(ds.getStepAmount2());
						count+=transactionManageDao
								.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
					}
					maa.setId(accessId);
					maa.setCurrency(info.getCurrency());
					
					maa.setAmount(ds.getStepAmount2()*info.getLimitCount()* -1);
					maa.setCashType("-11");// 交易提现入账
					maa.setAccountType("0");
					transactionManageDao.insertMerchantAccountAccess(maa);
					MerchantAccount merchantAccount = new MerchantAccount();
					merchantAccount.setId(maa.getAccountID());
					merchantAccount.setCashAmount(maa.getAmount());
					merchantAccount.setTotalAmount(maa.getAmount());
					transactionManageDao.updateMerchantAccount(merchantAccount);
				}
			}
			
			//阶梯三
			if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>=ds.getStart3()/100
					
					&&ds.getStart3()>0
					) {
				
				//开始从哪一笔后开始收取
				startLimit+=limitCount;
				if(ds.getEnd3()==-1 || ds.getEnd3()/100>=dc.getDisCount()*1.0/dc.getSuccessCount()){//全部收取
					limitCount=dc.getDisCount()-startLimit;
				}else{//区间部分收取
					try {
						limitCount=(int)Math.floor(ds.getEnd3()/100*dc.getSuccessCount())-startLimit;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				info.setLimitCount(limitCount);
				info.setStartLimit(startLimit);
				if(limitCount>0&&ds.getStepAmount3()>0){
					String accessId = Tools.getAccessId();
					// 只得到出入账明细 不做计算
					MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
					List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
					for (String tradeNo : tradeNos) {
						MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
						merchantAccountAccessDetail.setTradeNo(tradeNo);
						merchantAccountAccessDetail.setBatchNo(accessId);
						merchantAccountAccessDetail.setAccessId(accessId);
						merchantAccountAccessDetail.setAmount(ds.getStepAmount3());
						count+=transactionManageDao
								.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
					}
					maa.setId(accessId);
					maa.setCurrency(info.getCurrency());
					
					maa.setAmount(ds.getStepAmount3()*info.getLimitCount()* -1);
					maa.setCashType("-11");// 交易提现入账
					maa.setAccountType("0");
					transactionManageDao.insertMerchantAccountAccess(maa);
					MerchantAccount merchantAccount = new MerchantAccount();
					merchantAccount.setId(maa.getAccountID());
					merchantAccount.setCashAmount(maa.getAmount());
					merchantAccount.setTotalAmount(maa.getAmount());
					transactionManageDao.updateMerchantAccount(merchantAccount);
				}
			}
			
			//阶梯四
			if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>=ds.getStart4()/100
					
					&&ds.getStart4()>0
					) {
				
				//开始从哪一笔后开始收取
				startLimit+=limitCount;
				if(ds.getEnd4()==-1 || ds.getEnd4()/100>=dc.getDisCount()*1.0/dc.getSuccessCount()){//全部收取
					limitCount=dc.getDisCount()-startLimit;
				}else{//区间部分收取
					try {
						limitCount=(int)Math.floor(ds.getEnd4()/100*dc.getSuccessCount())-startLimit;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				info.setLimitCount(limitCount);
				info.setStartLimit(startLimit);
				if(limitCount>0&&ds.getStepAmount4()>0){
					String accessId = Tools.getAccessId();
					// 只得到出入账明细 不做计算
					MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
					List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
					for (String tradeNo : tradeNos) {
						MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
						merchantAccountAccessDetail.setTradeNo(tradeNo);
						merchantAccountAccessDetail.setBatchNo(accessId);
						merchantAccountAccessDetail.setAccessId(accessId);
						merchantAccountAccessDetail.setAmount(ds.getStepAmount4());
						count+=transactionManageDao
								.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
					}
					maa.setId(accessId);
					maa.setCurrency(info.getCurrency());
					
					maa.setAmount(ds.getStepAmount4()*info.getLimitCount()* -1);
					maa.setCashType("-11");// 交易提现入账
					maa.setAccountType("0");
					transactionManageDao.insertMerchantAccountAccess(maa);
					MerchantAccount merchantAccount = new MerchantAccount();
					merchantAccount.setId(maa.getAccountID());
					merchantAccount.setCashAmount(maa.getAmount());
					merchantAccount.setTotalAmount(maa.getAmount());
					transactionManageDao.updateMerchantAccount(merchantAccount);
				}
			}
			
			
			//阶梯五
			if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>=ds.getStart5()/100
					
					&&ds.getStart5()>0
					) {
				
				//开始从哪一笔后开始收取
				startLimit+=limitCount;
				if(ds.getEnd5()==-1 || ds.getEnd5()/100>=dc.getDisCount()*1.0/dc.getSuccessCount()){//全部收取
					limitCount=dc.getDisCount()-startLimit;
				}else{//区间部分收取
					try {
						limitCount=(int)Math.floor(ds.getEnd5()/100*dc.getSuccessCount())-startLimit;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				info.setLimitCount(limitCount);
				info.setStartLimit(startLimit);
				if(limitCount>0&&ds.getStepAmount5()>0){
					String accessId = Tools.getAccessId();
					// 只得到出入账明细 不做计算
					MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
					List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
					for (String tradeNo : tradeNos) {
						MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
						merchantAccountAccessDetail.setTradeNo(tradeNo);
						merchantAccountAccessDetail.setBatchNo(accessId);
						merchantAccountAccessDetail.setAccessId(accessId);
						merchantAccountAccessDetail.setAmount(ds.getStepAmount5());
						count+=transactionManageDao
								.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
					}
					maa.setId(accessId);
					maa.setCurrency(info.getCurrency());
					
					maa.setAmount(ds.getStepAmount5()*info.getLimitCount()* -1);
					maa.setCashType("-11");// 交易提现入账
					maa.setAccountType("0");
					transactionManageDao.insertMerchantAccountAccess(maa);
					MerchantAccount merchantAccount = new MerchantAccount();
					merchantAccount.setId(maa.getAccountID());
					merchantAccount.setCashAmount(maa.getAmount());
					merchantAccount.setTotalAmount(maa.getAmount());
					transactionManageDao.updateMerchantAccount(merchantAccount);
				}
			}
			
			
		}else if(info.getIsAllOrOver()==3){//拒付阶梯手续费
			// 通过设置条件查询符合要求的订单
			//查询商户是否复核拒付罚金收取
			//计算商户上个月的拒付率
			DisFineCount dc=transactionManageDao.queryMerchantDisRateByMerNoAndTerNo(info);
			DisFineStepInfo ds=transactionManageDao.queryDisFineStepInfoById(info.getStepId());
			
			//计算收取多少笔
			int limitCount=0;
			//计算从那一笔后开始收取
			int startLimit=0;
			double amount=0;
			//阶梯一
			if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>ds.getStart1()/100
					&&(dc.getDisCount()*1.0/dc.getSuccessCount()<=ds.getEnd1()/100 || ds.getEnd1()==-1)
					) {
				amount=ds.getStepAmount1();
				
			}
			//阶梯二
			if (amount ==0&&null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>ds.getStart2()/100
					&&(dc.getDisCount()*1.0/dc.getSuccessCount()<=ds.getEnd2()/100 || ds.getEnd2()==-1)
					) {
				amount=ds.getStepAmount2();
				
			}
			//阶梯三
			if (amount ==0&&null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>ds.getStart3()/100
					&&(dc.getDisCount()*1.0/dc.getSuccessCount()<=ds.getEnd3()/100 || ds.getEnd3()==-1)
					) {
				amount=ds.getStepAmount3();
				
			}
			//阶梯四
			if (amount ==0&&null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>ds.getStart4()/100
					&&(dc.getDisCount()*1.0/dc.getSuccessCount()<=ds.getEnd4()/100 || ds.getEnd4()==-1)
					) {
				amount=ds.getStepAmount4();
				
			}
			//阶梯五
			if (amount ==0&&null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 
					&&dc.getDisCount()*1.0/dc.getSuccessCount()>ds.getStart5()/100
					&&(dc.getDisCount()*1.0/dc.getSuccessCount()<=ds.getEnd5()/100 || ds.getEnd5()==-1)
					) {
				amount=ds.getStepAmount5();
				
			}
			limitCount=dc.getDisCount();
			info.setStartLimit(startLimit);
			info.setLimitCount(limitCount);
			
			if(limitCount>0&&amount>0){
				String accessId = Tools.getAccessId();
				// 只得到出入账明细 不做计算
				MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
				List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
				for (String tradeNo : tradeNos) {
					MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
					merchantAccountAccessDetail.setTradeNo(tradeNo);
					merchantAccountAccessDetail.setBatchNo(accessId);
					merchantAccountAccessDetail.setAccessId(accessId);
					merchantAccountAccessDetail.setAmount(amount);
					count+=transactionManageDao
							.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
				}
				maa.setId(accessId);
				maa.setCurrency(info.getCurrency());
				
				maa.setAmount(amount*info.getLimitCount()* -1);
				maa.setCashType("-11");// 交易提现入账
				maa.setAccountType("0");
				transactionManageDao.insertMerchantAccountAccess(maa);
				MerchantAccount merchantAccount = new MerchantAccount();
				merchantAccount.setId(maa.getAccountID());
				merchantAccount.setCashAmount(maa.getAmount());
				merchantAccount.setTotalAmount(maa.getAmount());
				transactionManageDao.updateMerchantAccount(merchantAccount);
			}
		}else{
			try{
				// 通过设置条件查询符合要求的订单
				//查询商户是否复核拒付罚金收取
				//计算商户上个月的拒付率
				DisFineCount dc=transactionManageDao.queryMerchantDisRateByMerNoAndTerNo(info);
				if (null != dc && dc.getSuccessCount()!=0 &&dc.getDisCount()!=0 &&dc.getDisCount()*1.0/dc.getSuccessCount()>=info.getDisRate()/100) {
					//计算收取多少笔
					int limitCount=0;
					if(info.getIsAllOrOver()==1){//全部收取
						limitCount=dc.getDisCount();
					}else{//超出部分收取
						try {
							limitCount=(int)Math.floor((dc.getDisCount()*1.0/dc.getSuccessCount()-info.getDisRate()/100)*dc.getSuccessCount());
							if(limitCount==0){
								limitCount=1;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					info.setStartLimit(0);
					info.setLimitCount(limitCount);
					String accessId = Tools.getAccessId();
					// 只得到出入账明细 不做计算
					MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
					List<String> tradeNos=transactionManageDao.queryConfirmTransDisFineOrder(info);
					for (String tradeNo : tradeNos) {
						MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
						merchantAccountAccessDetail.setTradeNo(tradeNo);
						merchantAccountAccessDetail.setBatchNo(accessId);
						merchantAccountAccessDetail.setAccessId(accessId);
						merchantAccountAccessDetail.setAmount(info.getAmount().doubleValue());
						count+=transactionManageDao
								.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);
					}
					maa.setId(accessId);
					maa.setCurrency(info.getCurrency());
					
					maa.setAmount(info.getAmount().doubleValue()*info.getLimitCount()* -1);
					maa.setCashType("-11");// 交易提现入账
					maa.setAccountType("0");
					transactionManageDao.insertMerchantAccountAccess(maa);
					MerchantAccount merchantAccount = new MerchantAccount();
					merchantAccount.setId(maa.getAccountID());
					merchantAccount.setCashAmount(maa.getAmount());
					merchantAccount.setTotalAmount(maa.getAmount());
					transactionManageDao.updateMerchantAccount(merchantAccount);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return count;
	}
	@Override
	public CheckToNoInfo queryCanTimeToPaymentCardInfo(CheckToNoInfo info1) {
		String dateStart=null;
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateStart=sdf.format(date)+" 01:30:00";
		try {
			if(sdf1.parse(dateStart).getTime()>date.getTime()){
				dateStart=sdf1.format(new Date(sdf1.parse(dateStart).getTime()-24*60*60*1000));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<CheckToNoInfo> list=transactionManageDao.queryCanTimeToPaymentCardInfo(dateStart,info1.getMerNo(),info1.getTerNo());
		if(list!=null&&list.size()>0){
			Random r=new Random();
			int index=r.nextInt(list.size());
			return list.get(index);
		}
		return null;
	}
	@Override
	public int updateCheckToNoInfo(CheckToNoInfo info) {
		return transactionManageDao.updateCheckToInfoByCheckToNo(info);
	}
	@Override
	public List<CheckToNoInfo> queryHowManyTimeNeedPay() {
		return transactionManageDao.queryHowManyTimeNeedPay();
	}
	
	@Override
	public List<RefundConfigInfo> queryRefundConfigInfoList() {
		return transactionManageDao.queryRefundConfigInfoList();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateRefundInfoReturnFlag(RefundConfigInfo config,
			RefundTransInfo trans, String configInfo) throws org.apache.http.ParseException, IOException {
		int a = transactionManageDao.updateRefundTransReturnFlagInfo(trans.getId(), 1);
		if(a>0){
			DefaultHttpClient httpClient = Tools.getHttpClient();
			HttpGet get = new HttpGet(configInfo);
			HttpResponse response = httpClient.execute(get);
			String result = EntityUtils.toString(response.getEntity());
			logger.info("返回结果:"+result);
		}
		return a;
	}
	
	@Override
	public List<MerchantWebSiteInfo> queryMerchantWebSiteInfo(int status,
			int month) {
		return transactionManageDao.queryMerchantWebSiteInfo(status, month);
	}
	
	@Override
	public int queryMerchantWebSiteTransCountInfo(String webSite, int month,
			String transType) {
		return transactionManageDao.queryMerchantWebSiteTransCountInfo(webSite, month, transType);
	}
	
	@Override
	public int updateMerchantWebSiteInfo(int id, int status, int operatedStatus,String remark) {
		return transactionManageDao.updateMerchantWebSiteInfo(id, status, operatedStatus,remark);
	}
	@Override
	public EmailInfo queryEmailInfo() {
		return transactionManageDao.queryEmailInfo();
	}
	
	@Override
	public List<SettleTypeInfo> canHandMerNo() {
		return transactionManageDao.canHandMerNo();
	}
	@Override
	public List<String> queryCanSettleHandTradeNos(SettleTypeInfo settleTypeInfo) {
		return transactionManageDao.queryCanSettleHandTradeNos(settleTypeInfo);
	}
	
	@Override
	public int updateHandTransStatusToAccess(List<String> tradeNos, int i) {
		return transactionManageDao.updateHandTransStatusToAccess(tradeNos,i);
	}
	
	@Override
	public List<TransCallbackInfo> queryTransCallbackInfoList() {
		return transactionManageDao.queryTransCallbackInfoList();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int deleteSuspiciousTransInfo() {
		//查询可疑订单六个月前最大的syspId
		int syspId=transactionManageDao.selectMaxSyspId();
		//通过syspId删除比这个小的所有的数据
		transactionManageDao.deleteFromMerTransSuspInfo(syspId);
		//删除是syspId小的可疑订单明细数据
		return transactionManageDao.deleteFromTransSuspInfo(syspId);
	}
	
	@Override
	public List<ThreatMetrixResultInfo> queryThreatMetrixDisTrans() {
		return transactionManageDao.queryThreatMetrixDisTrans();
	}
	
	@Override
	public List<TransInfoForExcel> queryTransInfoForExcelByDate(String date) {
		return transactionManageDao.queryTransInfoForExcelByDate(date);
	}
	
	@Override
	public List<PoolSettleInfo> queryNeedDateStr() {
		return transactionManageDao.queryNeedDateStr();
	}
	@Override
	public int insertDateForMerchantDateFormInfo(String date) {
		return transactionManageDao.insertDateForMerchantDateFormInfo(date);
	}
	@Override
	public int insertMerchantDateFormInfo(PoolSettleInfo dateStr) {
		//生成商户日报信息
		PoolSettleInfo info=transactionManageDao.queryMerchantDateFormInfo(dateStr);
		//查询上一个商户日报信息
		PoolSettleInfo info1=transactionManageDao.queryMerchantDateFormProInfo(dateStr);
		if(info==null){
			return 0;
		}
		if(info.getDateStr()==null){
			return 0;
		}
		if(info1==null){
			info1=new PoolSettleInfo();
			info1.setBalance(0D);
		}
		if(info.getDateStr().equals(info1.getDateStr())){
			return 0;
		}
		info.initTotalAmount();
		info.initOtherAmount();
		info.setBalance(info1.getBalance()+info.getTotalAmount());
		return transactionManageDao.insertMerchantDateFormInfo(info);
	}

	@Override
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForDisWarning() {
		return transactionManageDao.queryExceptionSettleInfoForDisWarning();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public int updateMerchantAccountForDisWarning(ExceptionSettleInfo info) {
		int count = 0;//成功收取拒付预警费记录数
		String accessId = Tools.getAccessId();//获取入账流水号
		//1.根据商户号终端号查询商户账户表
		MerchantAccountAccess maa = transactionManageDao.queryMerchantAccountForAccess(info.getMerNo(), info.getTerNo());
		//2.查询符合收取拒付预警费的订单 
		List<TransDisWarningOrder> transDisWarningOrderList=transactionManageDao.queryConfirmTransDisWarningOrder(info);
		if(null==transDisWarningOrderList || transDisWarningOrderList.size()==0){//无符合收取拒付预警费的订单 
			return count;
		}
		//拒付预警费的订单合计收取金额Amount
		BigDecimal amount=new BigDecimal(0);
		for (TransDisWarningOrder transDisWarningOrder : transDisWarningOrderList) {
			ExceptionSettleInfo exceptionSettleInfo=new ExceptionSettleInfo();
			exceptionSettleInfo.setMerNo(info.getMerNo());
			exceptionSettleInfo.setTerNo(info.getTerNo());
			exceptionSettleInfo.setCurrencyId(transDisWarningOrder.getCurrencyId());
			exceptionSettleInfo.setCardType(transDisWarningOrder.getCardType());
			//3-1.查询商户终端对拒付预警费的所有配置
			ExceptionSettleInfo settleInfo=transactionManageDao.queryAmountInfoForDisWarning(exceptionSettleInfo);
			String tradeNo=transDisWarningOrder.getTradeNo();
			logger.info("商户号："+info.getMerNo());
			logger.info("终端号："+info.getTerNo());
			logger.info("银行："+transDisWarningOrder.getBankName());
			logger.info("卡种："+transDisWarningOrder.getCardType());
			logger.info("交易流水号："+tradeNo);
			logger.info("拒付预警收取费用："+info.getCurrency()+" "+settleInfo.getAmount().doubleValue());
			//3-4.更新调查表的amount
			Complaint complaint=new Complaint();
			complaint.setTradeNo(tradeNo);
			complaint.setAmount(settleInfo.getAmount().doubleValue());
			transactionManageDao.updateConfirmTransDisWarningOrder(complaint);
			//3-5.插入商户账户出入账明细表
			MerchantAccountAccessDetail merchantAccountAccessDetail = new MerchantAccountAccessDetail();
			merchantAccountAccessDetail.setTradeNo(tradeNo);
			merchantAccountAccessDetail.setBatchNo(accessId);
			merchantAccountAccessDetail.setAccessId(accessId);
			merchantAccountAccessDetail.setAmount(settleInfo.getAmount().doubleValue());//拒付预警费
			count+=transactionManageDao.insertMerchantAccountAccessDetail(merchantAccountAccessDetail);//插入商户账户出入账明细表
			amount=amount.add(settleInfo.getAmount());//累加
		}
		//4.计算拒付预警费合计并插入商户账户出入账表
		maa.setId(accessId);
		maa.setCurrency(info.getCurrency());
		maa.setAmount(amount.negate().doubleValue());//拒付预警费合计转负取double值
		maa.setCashType(Constants.CASH_TYPE_DIS_WARNING);// -16拒付预警费入账
		maa.setAccountType(Constants.ACCOUNT_TYPE_TRANS_ACCOUNT);//0交易账户
		transactionManageDao.insertMerchantAccountAccess(maa);//插入商户账户出入账表
		//5.更新商户虚拟账户
		MerchantAccount merchantAccount = new MerchantAccount();
		merchantAccount.setId(maa.getAccountID());
		merchantAccount.setCashAmount(maa.getAmount());//可提现金额变动金额负值
		merchantAccount.setTotalAmount(maa.getAmount());//总金额变动金额负值
		transactionManageDao.updateMerchantAccount(merchantAccount);//更新商户虚拟账户
		return count;
	}
	
	@Override
	public List<IppTrackInfo> queryNeedsUploadTrackInfo() {
		return transactionManageDao.queryNeedsUploadTrackInfo();
	}
	@Override
	public void updateGoodsPressByTradeNo(String oid) {
		transactionManageDao.updateGoodsPressByTradeNo(oid);
	}
	
	@Override
	public List<String> queryEcpAuthOrderForCapture() {
		return transactionManageDao.queryEcpAuthOrderForCapture();
	}
	
	@Override
	public int ecpAuthCapture(String tradeNo) {
		int count=0;
		DefaultHttpClient httpClient = Tools.getHttpClient(); // 建立客户端连接
		HttpPost postMethod = new HttpPost(
				PaymentConfig.PAYMENT_ADDRESS+"ecp/confirmed/");
		List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
		nvps.add(new BasicNameValuePair("tradeNo", tradeNo));
		postMethod.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
		try {
			HttpResponse resp = httpClient.execute(postMethod); // 处理发送
			HttpEntity entity = resp.getEntity();
			String entityStr = EntityUtils.toString(entity, "UTF-8");
			JSONObject json = (JSONObject) JSONPParserUtil.parseJSONP(entityStr);
			String respCode=json.getString("respCode");
			logger.info("ecp预授权"+tradeNo+"自动确认结果："+entityStr);
			if("00".equals(respCode)){
				count=count+1;
			}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					postMethod.clone();
				} catch (CloneNotSupportedException e) {
					return count;
				}
			}
		return count;
	}
}
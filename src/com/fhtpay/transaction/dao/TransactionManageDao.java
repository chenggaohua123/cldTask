package com.fhtpay.transaction.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.email.model.GwTradeRecord;
import com.fhtpay.task.model.CheckToNoInfo;
import com.fhtpay.task.model.PoolSettleInfo;
import com.fhtpay.task.model.ThreatMetrixResultInfo;
import com.fhtpay.task.model.TransInfoForExcel;
import com.fhtpay.task.model.callback.TransCallbackInfo;
import com.fhtpay.task.model.callback.TransGoodsInfo;
import com.fhtpay.task.model.email.RiskTransInfo;
import com.fhtpay.task.model.refundReturn.RefundConfigInfo;
import com.fhtpay.task.model.refundReturn.RefundTransInfo;
import com.fhtpay.task.model.suspicious.ParamInfo;
import com.fhtpay.task.model.suspicious.ParamValueInfo;
import com.fhtpay.task.model.suspicious.RuleProcessClass;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTransInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransDeatailInfo;
import com.fhtpay.task.model.suspicious.SuspiciousTransOrderInfo;
import com.fhtpay.task.model.suspicious.TransParameterInfo;
import com.fhtpay.task.model.trace.MerchantInfo;
import com.fhtpay.task.model.trace.MerchantTraceInfo;
import com.fhtpay.task.model.trace.TraceCountryInfo;
import com.fhtpay.task.model.warn.BankCurrencyInfo;
import com.fhtpay.task.model.warn.TransWarnEmailInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.model.warn.TransWarnMessagInfo;
import com.fhtpay.task.model.warn.TransWarnPhoneInfo;
import com.fhtpay.task.model.website.MerchantWebSiteInfo;
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
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;
import com.fhtpay.transaction.model.MerchantTerInfo;
import com.fhtpay.transaction.model.PaymentInfo;
import com.fhtpay.transaction.model.SettleTypeInfo;
import com.fhtpay.transaction.model.TransCheckedInfo;
import com.fhtpay.transaction.model.TransDisWarningOrder;

public interface TransactionManageDao {
	/**
	 * 获取所有未执行的批量上传勾兑数据
	 * */
	public List<TransCheckedInfo> queryTransCheckedInfo();
	/**
	 * 根据上传信息勾兑交易记录
	 * */
	public int updateTransInfo(@Param("info")TransCheckedInfo transCheckedInfo);
	/**
	 * 根据流水号将勾兑交易表信息更新为勾兑异常
	 * @param tradeNo
	 */
	public int updateTransInfoExpByTradeNo(String tradeNo);
	/**
	 * 根据流水号将勾兑信息表跟新为勾兑异常
	 * */
	public int updateTransCheckedInfoExpByTradeNo(String tradeNo);
	/**
	 * 根据流水号将勾兑信息表跟新为已勾兑
	 * */
	public void updateTransCheckedInfoSuccessByTradeNo(String tradeNo);
	/**
	 * 查询没有虚拟账户的商户
	 * */
	public List<MerchantAccount> queryNoAccountMerchantInfo();
	/**
	 * 插入商户虚拟账户
	 * */
	public int insertMerchantAccount(@Param("info")MerchantAccount merchantAccount);
	/**
	 * 查询符合入账的交易订单
	 * */
	public List<String> queryTradeNoAccess();
	/**
	 * 查询符合入账的冻结订单
	 * */
	public List<String> queryDJTradeNoAccess();
	/**
	 * 查询符合入账的解冻订单
	 * */
	public List<String> queryJDTradeNoAccess();
	/**
	 * 查询符合入账的拒付订单
	 * */
	public List<String> queryJFTradeNoAccess();
	/**
	 * 查询符合入账的退款订单
	 * */
	public List<String> queryTKTradeNoAccess();
	/**
	 * 统计虚拟账户入账信息
	 * @param tradeNos
	 * */
	public List<MerchantAccountAccess> queryMerchantAccountTotalAmount(@Param("list")List<String> list,@Param("cashType")String cashType);
	/**
	 * 更新交易表入账记录
	 * */
	public int updateTransAccess(List<String> list);
	/**
	 * 插入入账明细
	 * */
	public int insertMerchantAccountAccess(@Param("info")MerchantAccountAccess merchantAccountAccess);
	/**
	 * 更新商户虚拟账户
	 * */
	public int updateMerchantAccount(@Param("info")MerchantAccount merchantAccount);
	/**
	 * 插入入账流水明细
	 * */
	public int insertMerchantAccountAccessDetail(@Param("info")MerchantAccountAccessDetail merchantAccountAccessDetail);
	/**
	 * 查询商户结算条件
	 * */
	public List<SettleTypeInfo> querySettleTypeInfo();
	/**
	 * 查询符合T+N结算的订单
	 * */
	public List<String> queryTradeNosForTN(@Param("info")SettleTypeInfo settleTypeInfo);
	/**
	 * 通过流水号更新结算批次号
	 * */
	public int updateTransBatchNo(@Param("list")List<String> list, @Param("batchNo")String batchNo);
	/**
	 * 通过流水号计算商户提现金额
	 * */
	public MerchantAccountAccess queryMerchantAccountCash(List<String> list);
	/**
	 * 通过结算条件查询符合结算保证金的订单号
	 * */
	public List<String> queryCanSettleBondTradeNos(@Param("info")SettleTypeInfo settleTypeInfo);
	/**
	 * 通过流水号计算商户保证金提现金额
	 * */
	public MerchantAccountAccess queryMerchantAccountBondCash(List<String> list);
	/**
	 * 更新保证金结算批次
	 * */
	public int updateTransBondBatchNo(@Param("list")List<String> list, @Param("bondBatchNo")String batchNo);
	/**
	 * 插入原始中行汇率记录
	 * */
	public int insertBankSourceInfo(@Param("list")List<String> list);
	/**
	 * 清空以前的中行汇率记录
	 * */
	public int deleteBankSourceRateInfo();
	/**
	 * 查询所有原始记录
	 * */
	public List<BankSourceInfo> queryBankSourceRateInfo();
	/**
	 * 插入转换后的币种到CNY汇率
	 * */
	public void insertBankSourceRateInfo(@Param("info")BankSourceRateInfo bankSourceRateInfo);
	/**
	 * 查询存在的汇率数量
	 * */
	public int countExistsRate(@Param("info")BankSourceRateInfo bankSourceRateInfo);
	/**
	 * 更新转换后的币种到CNY汇率
	 * */
	public void updateBankSourceRateInfo(@Param("info")BankSourceRateInfo bankSourceRateInfo);
	/**
	 * 更新汇率到日志表
	 * */
	public void insertBankSourceRateInfoLog();
	/***
	 * 查询符合妥投结算的订单
	 * */
	public List<String> queryCanSettleTradeNosBySigned(@Param("info")SettleTypeInfo settleTypeInfo);
	/**
	 * 查询待发送的邮件信息
	 * */
	public List<EmailSendInfo> queryEmailSendInfo();
	/**
	 * 更新邮件发送状态
	 * */
	public int updateEmailSendInfo(EmailSendInfo info);
	/**
	 * 查询邮箱信息
	 * */
	public EmailInfo queryEmailInfo();
	/**
	 * 通过交易流水号查询发送给持卡人的信息
	 * */
	public GwTradeRecord queryTradeRocordByTradeNo(EmailSendInfo info);
	/**
	 * 
	 * 通过ID更新邮箱发送次数
	 * @param info
	 * @return
	 */
	
	public int updateEmailSendCountById(EmailInfo info);
	/***
	 * 清零邮箱发送次数
	 * */
	public int updateEmailCount();
	/**
	 * 上传单号结算
	 * */
	public List<String> queryCanSettleTradeNosBySendGoods(@Param("info")SettleTypeInfo settleTypeInfo);
	
	/**
	 * 查询商户邮件发送配置
	 * @param merNo
	 * @param terNo
	 * @return
	 */
	public List<MerchantEmailConfigInfo> queryMerEmailConfigByMerNoAndTerNo(@Param("merNo") String merNo ,@Param("terNo") String terNo,@Param("emailSendType") String emailSendType);
	
	/**
	 * 异常订单查询
	 * @param tradeNo
	 * @param complaintType
	 * @return
	 */
	public Complaint queryComplaintTransByTradeNoAndComplainType(@Param("tradeNo") String tradeNo,@Param("complaintType") String complaintType);
	
	/**
	 * 查询商户终端信息
	 * @param merNo
	 * @param terNo
	 * @return
	 */
	public MerchantTerInfo queryMerchantTerInfoByMerNoAndTerNo(@Param("merNo") String merNo,@Param("terNo") String terNo);
	/**
	 * 查询需要更新的汇率信息
	 * */
	public List<ExchangeRateInfo> queryExchangRateInfo();
	/**
	 * 查询中行汇率
	 * */
	public BigDecimal queryBankRate(@Param("info")ExchangeRateInfo info);
	public BigDecimal queryBankRateCNY(@Param("info")ExchangeRateInfo info);
	/**
	 * 更新汇率异常
	 * */
	public int updateExceptionExchagRateInfo(@Param("info")ExchangeRateInfo info);
	
	/**
	 * 更新正常汇率
	 * */
	public int updateExchagRateInfo(@Param("info")ExchangeRateInfo info);
	/**
	 * 查询未标记时间列表
	 * @return
	 */
	public List<DuplicateTransCount> queryNoFlagDate();
	/**
	 * 查询当前时间没有标记的订单 订单按时间升序排列
	 * @param doDate
	 * @return
	 */
	public List<String> queryNoFlagTradeNos(@Param("doDate")String doDate);
	
	/**
	 * 判断当前订单 成功重复了多少次 失败重复了多少次
	 * @param tradeNo
	 * @return
	 * 
	 */
	public DuplicateTransCount queryDuplicateTransCount(@Param("tradeNo")String tradeNo,@Param("doDate")String doDate);
	/**
	 * 标记订单 1 重复 2不重复
	 * @param tradeNo
	 * @param i
	 * @return
	 */
	public int updateTransInfoDupplicateFlag(@Param("tradeNo")String tradeNo, @Param("i")int i);
	
	
	public List<String> queryBeforeFailTransInfoDuplicateFlag(@Param("doDate")String doDate,
			@Param("tradeNo")String tradeNo);
	/**
	 * 查询失败订单手续费收取
	 * @return
	 */
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForTransFail();
	/**
	 * 通过设置条件查询符合要求的订单
	 * @param info
	 * @return
	 */
	public List<String> queryConfirmTransFailOrder(ExceptionSettleInfo info);
	/**
	 * 通过商户号终端号查询商户的校验码
	 * @param merNo
	 * @param terNo
	 * @return sha
	 */
	public String getShaKeyByMerNoAndTerNo(@Param("merNo")String merNo, @Param("terNo")String terNo);
	/**
	 * 查询需要返回支付结果给商户的订单信息
	 * @return
	 */
	public List<PaymentInfo> queryMustBackTradeNos();
	
	/**
	 * 修改返回状态
	 */
	public int updateMustBackTradeNoFlag(@Param("list") List<PaymentInfo> list);
	
	/**
	 * 查找高风险订单
	 */
	public RiskTransInfo queryMerchantRiskTransInfo(@Param("tradeNo") String tradeNo);
	
	/*******************************可疑订单*************************************/
	/**
	 * 查找商户信息
	 */
	public List<SuspiciousMerInfo> querySuspiciousMerInfo();
	/**
	 * 查找可疑订单商户，终端
	 */
	public List<SuspiciousMerTerInfo> querySuspiciousMerTerInfo();
	/**
	 * 查找不符合规则信息
	 */
	public List<SuspiciousTransDeatailInfo> querySuspiciousLtTransInfoByMerNo(@Param("vo") SuspiciousInfo vo);
	/**
	 * 查找不符合规则信息
	 */
	public List<SuspiciousTransDeatailInfo> querySuspiciousLtTransInfoByMerNoAndTerNo(@Param("vo") SuspiciousInfo vo);
	
	/**
	 * 查找不符合规则信息
	 */
	public List<SuspiciousTransDeatailInfo> querySuspiciousNqTransInfoByMerNo(@Param("vo") SuspiciousInfo vo);
	/**
	 * 查找不符合规则信息
	 */
	public List<SuspiciousTransDeatailInfo> querySuspiciousNqTransInfoByMerNoAndTerNo(@Param("vo") SuspiciousInfo vo);
	/**
	 * 保存可疑商户交易信息
	 */
	public int saveSuspiciousMerTransInfo(@Param("vo") SuspiciousMerTransInfo vo);
	/**
	 * 保存可疑订单信息
	 */
	public int saveSuspiciousTransOrderInfo(@Param("vo") SuspiciousTransOrderInfo vo);
	/**
	 * 查找商户终端规则信息
	 */
	public List<RulesInfo> queryRuleInfoByMerNo(@Param("merNo") String merNo);
	/**
	 * 查找商户终端规则信息
	 */
	public List<RulesInfo> queryRuleInfoByMerNoAndTerNo(@Param("merNo") String merNo, @Param("terNo")String terNo);
	/**
	 * 查找商户终端规则信息
	 */
	public List<RulesInfo> queryRuleInfoByTerNo(@Param("terNo")String terNo);
	/**
	 * 根据参数ID查询参数信息
	 */
	public ParamInfo queryParamInfoByParamId(@Param("paramId") String paramId);
	/**
	 * 查询参数详细信息列表
	 */
	public List<ParamValueInfo> queryParamValuesInfoByParamId(@Param("paramId") String paramId);
	/**
	 * 查询参数列表的值
	 */
	public List<String> queryParamListValuesByParamId(@Param("paramId") String paramId);
	/**
	 * 根据规则类型,键值查找值信息
	 */
	public List<Map<String,String>> queryParamTableValues(@Param("tableName") String tableName, @Param("colKey")String colKey, @Param("colValue")String colValue);
	/**
	 * 根据处理类ID查询规则处理类
	 */
	public RuleProcessClass queryRuleProcessClassByClassId(@Param("classId") String classId);

	
	/**
	 * 查找交易预警信息
	 */
	public List<TransWarnInfo> queryTransWarnInfo();
	
	/**
	 * 查找银行通道信息
	 */
	public List<BankCurrencyInfo> queryBankCurrencyInfo(@Param("bankId") String bankId);
	
	/**
	 * 查找违反规则银行交易信息
	 */
	public int queryRespMsgTransInfo(@Param("vo") TransWarnInfo vo, @Param("currencyId") String currencyId);
	
	/**
	 * 查找连续交易失败信息
	 */
	public String queryFailedTransInfo(@Param("vo") TransWarnInfo vo, @Param("currencyId") String currencyId);
	
	/**
	 * 查找连续交易失败信息
	 */
	public List<String> queryFaildTransRespCodeInfo(@Param("vo") TransWarnInfo vo, @Param("currencyId") String currencyId, @Param("transId") String transId, @Param("limit") int limit);
	
	/**
	 * 查找未交易信息
	 */
	public int queryNoTransInfo(@Param("vo") TransWarnInfo vo, @Param("currencyId") String currencyId);
	
	/**
	 * 查风险交易信息
	 */
	public int queryRiskTransInfo(@Param("vo") TransWarnInfo vo, @Param("currencyId") String currencyId);
	
	/**
	 * 查找电话信息
	 */
	public TransWarnPhoneInfo queryTransWarnPhoneInfo(@Param("warnId") String warnId);
	
	/**
	 * 查找预警邮箱信息
	 */
	public List<TransWarnEmailInfo> queryTransWarnEmailInfo(@Param("vo") TransWarnInfo vo);
	
	/**
	 * 查找预警信息
	 */
	public TransWarnMessagInfo queryTransWarnMessageInfoByWarnId(@Param("warnId") String warnId);

	/**
	 * 查询需要更新的南粤订单
	 * @return
	 */
	public List<String> queryNeedChangeTransactionTradeNos();
	
	/**
	 * 查询收取拒付罚金的商户
	 * @return
	 */
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForDisFine();
	/**
	 * 查询商户的拒付率 
	 * rate=商户的拒付通知笔数(商户可见通知笔数)/当月商户交易成功笔数
	 * @param info
	 * @return
	 */
	public DisFineCount queryMerchantDisRateByMerNoAndTerNo(ExceptionSettleInfo info);
	
	/**
	 * 根据商户号终端号查询商户账户表
	 * @param merNo
	 * @param terNo
	 * @return
	 */
	public MerchantAccountAccess queryMerchantAccountForAccess(@Param("merNo")String merNo,@Param("terNo")String terNo);
	/**
	 * 查询符合拒付罚金的订单
	 * @param info
	 * @return
	 */
	public List<String> queryConfirmTransDisFineOrder(ExceptionSettleInfo info);

	/**
	 * 查询收取拒付预警费的商户
	 * @return
	 */
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForDisWarning();
	
	/**
	 * 查询收取拒付预警费的商户的定制费用  优先级依次选择卡种定制、银行定制、所有
	 * @return
	 */
	public ExceptionSettleInfo queryAmountInfoForDisWarning(ExceptionSettleInfo info);

	/**
	 * 查询符合拒付预警费的订单
	 * @param info
	 * @return
	 */
	public List<TransDisWarningOrder> queryConfirmTransDisWarningOrder(ExceptionSettleInfo info);
	
	/**
	 * 根据交易流水号更新已经收取拒付预警费的订单
	 * @param complaint
	 * @return
	 */
	public int updateConfirmTransDisWarningOrder(@Param("complaint")Complaint complaint);
	
	/**
	 * 查询可以交易的卡信息
	 * @param dateStart
	 * @param terNo 
	 * @param merNo 
	 * @param dateEnd
	 * @return
	 */
	public List<CheckToNoInfo> queryCanTimeToPaymentCardInfo(@Param("dateStart")String dateStart, @Param("merNo")String merNo,  @Param("terNo")String terNo);
	
	public int updateCheckToInfoByCheckToNo(CheckToNoInfo info);
	public List<CheckToNoInfo> queryHowManyTimeNeedPay();
	
	/**
	 * 查询退款返回网址配置信息
	 */
	public List<RefundConfigInfo> queryRefundConfigInfoList();
	
	/**
	 * 查询退款信息
	 */
	public List<RefundTransInfo> queryRefundTransInfo(@Param("merNo") String merNo, @Param("terNo") String terNo, @Param("flag") int flag, @Param("returnFlag") int returnFlag);
	
	/**
	 * 修改退款返回标记
	 */
	public int updateRefundTransReturnFlagInfo(@Param("id") int id, @Param("returnFlag") int returnFlag);
	
	/**
	 * 查找网址信息
	 */
	public List<MerchantWebSiteInfo> queryMerchantWebSiteInfo(@Param("status") int status, @Param("month") int month);
	
	/**
	 * 查找网址交易记录总数
	 */
	public int queryMerchantWebSiteTransCountInfo(@Param("webSite") String webSite, @Param("month") int month, @Param("transType") String transType);
	
	/**
	 * 修改网址记录状态
	 */
	public int updateMerchantWebSiteInfo(@Param("id") int id, @Param("status") int status, @Param("operatedStatus") int operatedStatus,@Param("remark")String remark);
	
	/**
	 * 查询发送持卡人回访邮件
	 */
	public List<TransCallbackInfo> queryTransCallbackInfoList();
	
	/**
	 * 修改发送邮件状态
	 */
	public int updateTransCallbackInfo(@Param("id") int id, @Param("sendStatus") int sendStatus);
	
	/**
	 * 查询货物信息
	 */
	public List<TransGoodsInfo> queryTransGoodsInfoList(@Param("tradeNo") String tradeNo);
	
	/**
	 * 根据邮箱名查询邮箱
	 */
	public EmailInfo queryEmailInfoByEmail(@Param("email") String email);

	
	/**
	 * 查询可以手工入账的商户号
	 * @return
	 */
	public List<SettleTypeInfo> canHandMerNo();
	/**
	 * 通过商户号终端号查询可以手工结算的订单
	 * @param settleTypeInfo
	 * @return
	 */
	public List<String> queryCanSettleHandTradeNos(@Param("info")SettleTypeInfo settleTypeInfo);
	/**
	 * 将手工入账的订单状态修改为已入账
	 * @param tradeNos
	 * @param i
	 * @return
	 */
	public int updateHandTransStatusToAccess(@Param("list")List<String> tradeNos,@Param("isAccess") int isAccess);
	
	/**
	 * 查询妥投结算商户的运单信息
	 */
	public List<MerchantTraceInfo> queryMerchantTraceInfoList(@Param("Iogistics") String Iogistics, @Param("merNo") String merNo);
	
	/**
	 * 查询妥投结算商户
	 */
	public List<MerchantInfo> queryMerchantInfoList();
	
	/**
	 * 修改运单状态
	 */
	public int updateGoodsPressOperationStatusById(@Param("id") int id, @Param("operationStatus") int operationStatus);
	
	/**
	 * 查询国家信息
	 */
	public TraceCountryInfo queryTraceCountryInfoByCountryNameCN(@Param("countryNameCN") String countryNameCN);
	/**
	 * 查询阶梯收费标准
	 * @param stepId
	 * @return
	 */
	public DisFineStepInfo queryDisFineStepInfoById(int stepId);
	/**
	 * 查询六个月最大的syspId
	 * @return
	 */
	public int selectMaxSyspId();
	/**
	 * //通过syspId删除比这个小的所有的数据
	 * @param syspId
	 * @return
	 */
	public int deleteFromMerTransSuspInfo(int syspId);
	/**
	 * 删除是syspId小的可疑订单明细数据
	 * @param syspId
	 * @return
	 */
	public int deleteFromTransSuspInfo(int syspId);
	/**
	 * 查询符合抛送给ThreatMetrix的拒付订单
	 * @return
	 */
	public List<ThreatMetrixResultInfo> queryThreatMetrixDisTrans();
	/**
	 * 查询当天商户成功订单的信息
	 * @param merTerInfo
	 * @param paramName
	 * @return
	 */
	public List<TransParameterInfo> queryTransParameterInfo(
			@Param("mer")SuspiciousMerTerInfo merTerInfo, @Param("info")String paramName,
			@Param("param1")String mainParameter,@Param("param2")String param2);
	/**
	 * 匹配参数具备退款以及拒付的内容
	 * @param ruleParam
	 * @param info
	 * @param count
	 * @return
	 */
	public List<SuspiciousTransDeatailInfo> querySameParameterMatchRefundDis(
			@Param("rule")ParamInfo ruleParam, @Param("info")TransParameterInfo info, 
			@Param("count")int count,@Param("paramType")int paramType,@Param("mainParameter")String mainParameter);
	/**
	 * 匹配参数具备不同参数成功的内容
	 * @param ruleParam
	 * @param info
	 * @param count
	 * @return
	 */
	public List<SuspiciousTransDeatailInfo> querySameParameterMatchDifferentSuccess(
			@Param("rule")ParamInfo ruleParam, @Param("info")TransParameterInfo info, 
			@Param("count")int count,@Param("paramType")int paramType,@Param("mainParameter")String mainParameter,@Param("matchParameter")String matchParameter);
	/**
	 * 匹配参数具备不同参数退款或者拒付的内容
	 * @param ruleParam
	 * @param info
	 * @param count
	 * @return
	 */
	public List<SuspiciousTransDeatailInfo> querySameParameterMatchDifferentRefundDis(
			@Param("rule")ParamInfo ruleParam, @Param("info")TransParameterInfo info, @Param("count")int count
			,@Param("paramType")int paramType,@Param("mainParameter")String mainParameter,@Param("matchParameter")String matchParameter);
	
	/**
	 * 查询今天的交易信息
	 * @return
	 */
	public List<TransInfoForExcel> queryTransInfoForExcelByDate(@Param("date")String date);
	/**
	 * 查询需要做日报的日期
	 * @return
	 */
	public List<PoolSettleInfo> queryNeedDateStr();
	/**
	 * 插入商户日报时间轴
	 * @return
	 */
	public int insertDateForMerchantDateFormInfo(String date);
	/**
	 * 查询商户要生成的日报信息
	 * @param dateStr
	 * @return
	 */
	public PoolSettleInfo queryMerchantDateFormInfo(PoolSettleInfo dateStr);
	/**
	 * 查询上一个商户日报信息
	 * @param dateStr
	 * @return
	 */
	public PoolSettleInfo queryMerchantDateFormProInfo(PoolSettleInfo dateStr);
	/**
	 * 插入商户日报信息
	 * @param info
	 * @return
	 */
	public int insertMerchantDateFormInfo(PoolSettleInfo info);
	
	public List<IppTrackInfo> queryNeedsUploadTrackInfo();
	public int updateGoodsPressByTradeNo(String oid);
	
	/**
	 * 查询超过4天未确认的ecp预授权成功的订单
	 * @return
	 */
	List<String> queryEcpAuthOrderForCapture();
}

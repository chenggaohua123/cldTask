package com.fhtpay.transaction.service;

import java.io.IOException;
import java.util.List;

import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.task.model.CheckToNoInfo;
import com.fhtpay.task.model.PoolSettleInfo;
import com.fhtpay.task.model.ThreatMetrixResultInfo;
import com.fhtpay.task.model.TransInfoForExcel;
import com.fhtpay.task.model.callback.TransCallbackInfo;
import com.fhtpay.task.model.refundReturn.RefundConfigInfo;
import com.fhtpay.task.model.refundReturn.RefundTransInfo;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.model.warn.TransWarnEmailInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.model.website.MerchantWebSiteInfo;
import com.fhtpay.transaction.model.DuplicateTransCount;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.ExceptionSettleInfo;
import com.fhtpay.transaction.model.IppTrackInfo;
import com.fhtpay.transaction.model.MerchantAccount;
import com.fhtpay.transaction.model.PaymentInfo;
import com.fhtpay.transaction.model.SettleTypeInfo;
import com.fhtpay.transaction.model.TransCheckedInfo;

public interface TransactionManageService {
	/**
	 * 获取所有未执行的批量上传勾兑数据
	 * */
	public List<TransCheckedInfo> queryTransCheckedInfo();
	/**
	 * 勾兑交易记录
	 * */
	public int updateTransChecked(List<TransCheckedInfo> list);
	/**
	 * 查询没有虚拟账户的商户
	 * */
	public List<MerchantAccount> queryNoAccountMerchantInfo();
	/**
	 * 添加商户虚拟账户
	 * */
	public int insertMerchantAccount(List<MerchantAccount> list);
	/**
	 * 查询符合入账要求的订单
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
	 * 总金额及保证及入账
	 * */
	public int updateMerchantAccountTotalAmount(List<String> list,String accessType);
	/**
	 * 查询所有的商户结算条件
	 * */
	public List<SettleTypeInfo> querySettleTypeInfo();
	/**
	 * 更新商户虚拟账户现金余额
	 * @param settleTypeInfo 
	 * */
	public int updateMerchantAccountCashAmount(List<String> tradeNos, SettleTypeInfo settleTypeInfo);
	/**
	 * 通过结算条件查询符合结算保证金的订单号
	 * */
	public List<String> queryCanSettleBondTradeNos(SettleTypeInfo settleTypeInfo);
	/**
	 * 更新虚拟账户保证金余额
	 * */
	public int updateMerchantAccountBondCashAmount(List<String> tradeNos,SettleTypeInfo settleTypeInfo);
	/**
	 * 插入中国银行原始汇率记录 并更新待审核的汇率
	 * */
	public int insertSourceBCRateInfo(List<String> arr);
	/**
	 * 查询待发送的邮件信息
	 * */
	public List<EmailSendInfo> queryEmailSendInfo();
	/**
	 * 更新邮件发送状态
	 * */
	public int updateEmailSendInfo(EmailSendInfo info);
	/***
	 * 清零邮箱发送次数
	 * *
	 * @return
	 */
	public int updateEmailCount();
	/**
	 * 查询没有标记的日期列表 yyyy-MM-dd
	 * */
	public List<DuplicateTransCount> queryNoFlagDate();
	/**
	 * 通过时间查询没有标记的流水号
	 * */
	public List<String> queryNoFlagTradeNos(String doDate);
	/**
	 * 标记重复或者不重复的订单
	 * */
	public int updateTransInfoDupplicateFlag(String tradeNo,String doDate);
	
	/**
	 *  查询结算条件中收取失败订单手续费的
	 * @return
	 */
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForTransFail();
	/**
	 * 收取符合条件失败订单的处理费
	 * @param info
	 * @return
	 */
	public int updateChargeForTransFailOrder(ExceptionSettleInfo info);
	/**
	 * 通过商户号终端号查询商户校验码
	 * @param merNo
	 * @param terNo
	 * @return
	 */
	public String getShaKeyByMerNoAndTerNo(String merNo, String terNo);
	/**
	 * 查询需要返回给商户的订单信息
	 * @return
	 */
	public List<PaymentInfo> queryMustBackTradeNos();
	
	/**
	 * 修改返回状态
	 */
	public int updateMustBackTradeNoFlag(List<PaymentInfo> list);
	
	/**
	 * 查找可疑订单商户信息
	 */
	public List<SuspiciousMerInfo> querySuspiciousMerInfo();
	/**
	 * 查找可疑订单商户，终端
	 */
	public List<SuspiciousMerTerInfo> querySuspiciousMerTerInfo();
	/**
	 * 启用处理类
	 */
	public String startSuspiciousProcessClass(List<RulesInfo> list, SuspiciousMerTerInfo merTerInfo, String susType);
	/**
	 * 查找商户规则信息
	 */
	public List<RulesInfo> queryRuleInfoByMerNo(String merNo);
	/**
	 * 查找商户终端规则信息
	 */
	public List<RulesInfo> queryRuleInfoByMerNoAndTerNo(String merNo,String terNo);

	
	/**
	 * 查找交易预警信息
	 */
	public List<TransWarnInfo> queryTransWarnInfo();
	
	/**
	 * 查找预警邮箱信息
	 */
	public List<TransWarnEmailInfo> queryTransWarnEmailInfo(TransWarnInfo info);

	/**
	 * 查询需要更新交易状态的南粤订单
	 * @return
	 */
	public List<String> queryNeedChangeTransactionTradeNos();
	
	/**
	 * 查询结算条件中收取拒付罚金的商户
	 * @return
	 */
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForDisFine();
	/**
	 * 按商户号收取拒付罚金
	 * @param info
	 * @return
	 */
	public int updateMerchantAccountForDisFine(ExceptionSettleInfo info);
	
	/**
	 * 查询可以抛送到银行的随机卡信息
	 * @param info1 
	 * @return
	 */
	public CheckToNoInfo queryCanTimeToPaymentCardInfo(CheckToNoInfo info1);
	public int updateCheckToNoInfo(CheckToNoInfo info);
	public List<CheckToNoInfo> queryHowManyTimeNeedPay();
	
	/**
	 * 查询退款返回网址配置信息
	 */
	public List<RefundConfigInfo> queryRefundConfigInfoList();
	
	/**
	 * 修改退款返回配置网址信息状态
	 */
	public int updateRefundInfoReturnFlag(RefundConfigInfo config, RefundTransInfo trans, String configInfo) throws org.apache.http.ParseException, IOException;
	
	/**
	 * 查找网址信息
	 */
	public List<MerchantWebSiteInfo> queryMerchantWebSiteInfo(int status, int month);
	
	/**
	 * 查询网址交易数量
	 */
	public int queryMerchantWebSiteTransCountInfo(String webSite, int month, String transType);
	
	/**
	 * 修改网址信息
	 */
	public int updateMerchantWebSiteInfo(int id, int status, int operatedStatus,String remark);
	/**
	 * 查询邮箱信息
	 * @return
	 */
	public EmailInfo queryEmailInfo();
	
	/**
	 * 查询可以手工入账的商户号以及终端号
	 * @return
	 */
	public List<SettleTypeInfo> canHandMerNo();
	/**
	 * 查询可以手工入账的订单号
	 * @param settleTypeInfo
	 * @return
	 */
	public List<String> queryCanSettleHandTradeNos(SettleTypeInfo settleTypeInfo);
	/**
	 * 将手工入账订单的状态更新为已入账 
	 * @param tradeNos
	 * @param i
	 * @return
	 */
	public int updateHandTransStatusToAccess(List<String> tradeNos, int i);
	
	/**
	 * 查询发送持卡人回访邮件
	 */
	public List<TransCallbackInfo> queryTransCallbackInfoList();
	/**
	 * 定时删除可疑订单数据
	 * @return
	 */
	public int deleteSuspiciousTransInfo();
	/**
	 * 查询符合抛送给ThreatMetrix的拒付订单
	 * @return
	 */
	public List<ThreatMetrixResultInfo> queryThreatMetrixDisTrans();
	/**
	 * 查询今天的交易信息
	 * @return
	 */
	public List<TransInfoForExcel> queryTransInfoForExcelByDate(String date);
	/**
	 * 查询需要日报的日期
	 * @return
	 */
	public List<PoolSettleInfo> queryNeedDateStr();
	/**
	 * 插入商户日报时间轴
	 * @param date
	 */
	public int insertDateForMerchantDateFormInfo(String date);
	/**
	 * 插入商户日报信息
	 * @param dateStr
	 * @return
	 */
	public int insertMerchantDateFormInfo(PoolSettleInfo dateStr);
	
	public List<IppTrackInfo> queryNeedsUploadTrackInfo();
	public void updateGoodsPressByTradeNo(String oid);

	/**
	 * 查询结算条件中需要收取拒付预警费的商户
	 * @return
	 */
	public List<ExceptionSettleInfo> queryExceptionSettleInfoForDisWarning();
	
	/**
	 * 按商户号收取拒付预警费
	 * @param info
	 * @return
	 */
	public int updateMerchantAccountForDisWarning(ExceptionSettleInfo info);
	
	/**
	 * 查询超过4天未确认的ecp预授权成功的订单
	 * @return
	 */
	public List<String> queryEcpAuthOrderForCapture();
	
	/**
	 * ecp预授权确认
	 * @param info
	 * @return
	 */
	public int ecpAuthCapture(String tradeNo);
}

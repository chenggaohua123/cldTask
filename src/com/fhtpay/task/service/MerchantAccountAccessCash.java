package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.test.MyTest;
import com.fhtpay.transaction.model.SettleTypeInfo;
import com.fhtpay.transaction.service.TransactionManageService;
/***
 * 商户虚拟账户现金入账
 * @author gaoyuan
 * */
public class MerchantAccountAccessCash extends BaseTaskJobDetail {
	private TransactionManageService transactionManageService;
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		//transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		transactionManageService = (TransactionManageService)MyTest.getInstance().getBeanFactory().getBean("transactionManageService");
		List<SettleTypeInfo> list=transactionManageService.querySettleTypeInfo();
		for(SettleTypeInfo settleTypeInfo:list){
			//MerchantAccountCash merchantAccountCash=(MerchantAccountCash) TaskMain.getInstance().getBeanFactory().getBean(settleTypeInfo.getSettleService());
			MerchantAccountCash merchantAccountCash=(MerchantAccountCash) MyTest.getInstance().getBeanFactory().getBean(settleTypeInfo.getSettleService());
			List<String> tradeNos=merchantAccountCash.queryCanSettleTradeNos(settleTypeInfo);
			if(null!=tradeNos&&tradeNos.size()>0){
				int count=transactionManageService.updateMerchantAccountCashAmount(tradeNos,settleTypeInfo);
				getLogger().info("商户号:"+settleTypeInfo.getMerNo()+" 终端号："+settleTypeInfo.getTerNo()+" 可提现金额成功入账了"+count+"笔记录！"+"------");
			}else{
				getLogger().info("商户号:"+settleTypeInfo.getMerNo()+" 终端号："+settleTypeInfo.getTerNo()+" 没有可提现金额入账的记录！");
			}
		}
		//超过180天的订单手工入账
		List<SettleTypeInfo> handTransMerNos=transactionManageService.canHandMerNo();
		for(SettleTypeInfo settleTypeInfo:handTransMerNos){
			List<String> tradeNos=transactionManageService.queryCanSettleHandTradeNos(settleTypeInfo);
			if(null!=tradeNos&&tradeNos.size()>0){
				int count=transactionManageService.updateMerchantAccountCashAmount(tradeNos,settleTypeInfo);
				if(count==tradeNos.size()){//更新手工订单状态
					transactionManageService.updateHandTransStatusToAccess(tradeNos,1);
				}
				getLogger().info("商户号:"+settleTypeInfo.getMerNo()+" 终端号："+settleTypeInfo.getTerNo()+" 成功手工入账了可提现金额"+count+"笔记录！"+"------");
			}else{
				getLogger().info("商户号:"+settleTypeInfo.getMerNo()+" 终端号："+settleTypeInfo.getTerNo()+" 没有要手工提现金额入账的记录！");
			}
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.test.MyTest;
import com.fhtpay.transaction.model.SettleTypeInfo;
import com.fhtpay.transaction.service.TransactionManageService;

public class MerchantAccountBondCash extends BaseTaskJobDetail {
	private TransactionManageService transactionManageService;
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		//transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		transactionManageService = (TransactionManageService)MyTest.getInstance().getBeanFactory().getBean("transactionManageService");
		List<SettleTypeInfo> list=transactionManageService.querySettleTypeInfo();
		for(SettleTypeInfo settleTypeInfo:list){
			List<String> tradeNos=transactionManageService.queryCanSettleBondTradeNos(settleTypeInfo);
			if(null!=tradeNos&&tradeNos.size()>0){
				int count=transactionManageService.updateMerchantAccountBondCashAmount(tradeNos,settleTypeInfo);
				getLogger().info("商户号:"+settleTypeInfo.getMerNo()+" 终端号："+settleTypeInfo.getTerNo()+" 成功提现了"+count+"笔保证金记录！"+"------");
			}else{
				getLogger().info("商户号:"+settleTypeInfo.getMerNo()+" 终端号："+settleTypeInfo.getTerNo()+" 没有要提现的保证金记录！");
			}
		}
		
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

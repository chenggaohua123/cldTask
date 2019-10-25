package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.test.MyTest;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 商户虚拟账户总金额及保证金入账定时任务
 * */
public class MerchantAccountTotalAmount extends BaseTaskJobDetail {
	private TransactionManageService transactionManageService;
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		//transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		transactionManageService = (TransactionManageService)MyTest.getInstance().getBeanFactory().getBean("transactionManageService");
		/***正常交易入账**/
		List<String> list=transactionManageService.queryTradeNoAccess();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.updateMerchantAccountTotalAmount(list,"0");
			getLogger().info("------"+" 成功入账了:"+count+"笔记录！"+"------");
		}else{
			getLogger().info("没有要入账的交易记录！");
		}
		/***冻结交易入账**/
		list=transactionManageService.queryDJTradeNoAccess();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.updateMerchantAccountTotalAmount(list,"-1");
			getLogger().info("------"+" 成功入账了:"+count+"笔冻结记录！"+"------");
		}else{
			getLogger().info("没有要入账的冻结记录！");
		}
		/***解冻交易入账**/
		list=transactionManageService.queryJDTradeNoAccess();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.updateMerchantAccountTotalAmount(list,"-2");
			getLogger().info("------"+" 成功入账了:"+count+"笔解冻记录！"+"------");
		}else{
			getLogger().info("没有要入账的解冻记录！");
		}
		/***拒付交易入账**/
		list=transactionManageService.queryJFTradeNoAccess();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.updateMerchantAccountTotalAmount(list,"-4");
			getLogger().info("------"+" 成功入账了:"+count+"笔拒付记录！"+"------");
		}else{
			getLogger().info("没有要入账的拒付记录！");
		}
		/***退款交易入账**/
		list=transactionManageService.queryTKTradeNoAccess();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.updateMerchantAccountTotalAmount(list,"-3");
			getLogger().info("------"+" 成功入账了:"+count+"笔退款记录！"+"------");
		}else{
			getLogger().info("没有要入账的退款记录！");
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

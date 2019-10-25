package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.test.MyTest;
import com.fhtpay.transaction.model.ExceptionSettleInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 拒付罚金每月定时任务 11
 * 
 * @author gaoyuan
 * */
public class MerchantAccountDisFine extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		//transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		transactionManageService = (TransactionManageService) MyTest.getInstance().getBeanFactory().getBean("transactionManageService");
		
		List<ExceptionSettleInfo> list=transactionManageService.queryExceptionSettleInfoForDisFine();
		int count=0;
		for(ExceptionSettleInfo info:list){
			count+=transactionManageService.updateMerchantAccountForDisFine(info);
		}
		getLogger().info("-----------------------成功收取了"+count+"条拒付罚金费用----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
	}

}

package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.model.ExceptionSettleInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 失败订单每天定时任务 8点执行 收取前一天的失败订单
 * 
 * @author gaoyuan
 * */
public class MerchantAccountTransFail extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		
		List<ExceptionSettleInfo> list=transactionManageService.queryExceptionSettleInfoForTransFail();
		int count=0;
		for(ExceptionSettleInfo info:list){
			count+=transactionManageService.updateChargeForTransFailOrder(info);
		}
		getLogger().info("-----------------------成功收取了"+count+"条失败订单的费用----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
	}

}

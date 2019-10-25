package com.fhtpay.task.service;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 每日定时删除六个月以前的可疑订单数据
 * 
 * @author gaoyuan
 * */
public class FixedTimeDeleteSuspiciousTransInfo extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		
		
		int count =transactionManageService.deleteSuspiciousTransInfo();
		getLogger().info("-----------------------成功删除了"+count+"条可疑订单数据----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
	}

}

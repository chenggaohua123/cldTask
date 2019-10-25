package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.model.ExceptionSettleInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 定时收取拒付预警手续费
 * 
 * @author gaoyuan
 * */
public class MerchantAccountDisWarning extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----" + taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");

		List<ExceptionSettleInfo> list = transactionManageService.queryExceptionSettleInfoForDisWarning();
		getLogger().info("-----------------------查询出" + list.size() + "条开通拒付预警的商户----------------------------");
		int count = 0;
		for (ExceptionSettleInfo info : list) {
			count += transactionManageService.updateMerchantAccountForDisWarning(info);
		}
		getLogger().info("-----------------------成功收取了" + count + "条拒付预警费用----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----" + taskInfo.getJobGroup() + "--------excu end-----");
	}

}

package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 定时对超过4天的预授权成功的订单自动确认
 * @author honghao
 * @version 创建时间：2019年7月18日  下午2:20:21
 */
public class AutoEcpCaptureService extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----" + taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		//查询超过4天预授权成功没有确认的订单
		List<String> tradeNoList = transactionManageService.queryEcpAuthOrderForCapture();
		getLogger().info("-----------------------查询出" + tradeNoList.size() + "条需要自动确认的预授权订单----------------------------");
		int count = 0;
		for (String tradeNo : tradeNoList) {
			count += transactionManageService.ecpAuthCapture(tradeNo);
		}
		getLogger().info("-----------------------成功确认了" + count + "条预授权订单----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----" + taskInfo.getJobGroup() + "--------excu end-----");
	}

}

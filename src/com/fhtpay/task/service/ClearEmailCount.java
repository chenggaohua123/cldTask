package com.fhtpay.task.service;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 定时执行批量上传勾兑任务
 * @author gaoyuan
 * */
public class ClearEmailCount extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		int i=0;
		i=transactionManageService.updateEmailCount();
		if(i>0){
			getLogger().info("------"+"成功清零了:"+i+"笔email记数 ！"+"------");
		}else{
			getLogger().info("没有要清零的邮箱计数！");
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.model.TransCheckedInfo;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 定时执行批量上传勾兑任务
 * @author gaoyuan
 * */
public class TransCheckedServiceImpl extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		List<TransCheckedInfo> list=transactionManageService.queryTransCheckedInfo();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.updateTransChecked(list);
			getLogger().info("------"+"成功勾兑:"+count+"笔记录！"+"------");
		}else{
			getLogger().info("没有要勾兑的上传记录！");
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

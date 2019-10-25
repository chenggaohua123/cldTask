package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.test.MyTest;
import com.fhtpay.transaction.model.MerchantAccount;
import com.fhtpay.transaction.service.TransactionManageService;

public class CreateMerchantAccount extends BaseTaskJobDetail {
	private TransactionManageService transactionManageService;
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		//transactionManageService = (TransactionManageService)MyTest.getInstance().getBeanFactory().getBean("transactionManageService");
		List<MerchantAccount> list=transactionManageService.queryNoAccountMerchantInfo();
		if(null!=list&&list.size()>0){
			int count=transactionManageService.insertMerchantAccount(list);
			getLogger().info("------"+"成功添加:"+count+"个商户虚拟账户！"+"------");
		}else{
			getLogger().info("没有要添加的商户虚拟账户！");
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

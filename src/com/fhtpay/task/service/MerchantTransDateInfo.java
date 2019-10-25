package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.PoolSettleInfo;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 定时执行每日重复订单标记
 * @author gaoyuan
 * */
public class MerchantTransDateInfo extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		//查询需要做日报的日期及商户
		List<PoolSettleInfo> doDateList=transactionManageService.queryNeedDateStr();
		int count=0;
		String date=null;
		for(PoolSettleInfo dateStr:doDateList){
			if(date==null){
				date=dateStr.getDateStr();
			}
			if(!date.equals(dateStr.getDateStr())){
				getLogger().info("------插入"+date+"商户交易日报完成-----");
				transactionManageService.insertDateForMerchantDateFormInfo(date);
				date=dateStr.getDateStr();
			}
			//存储当前日期当前交易的商户日报
			count+=transactionManageService.insertMerchantDateFormInfo(dateStr);
		}
		if(date != null ){
			getLogger().info("------插入"+date+"商户交易日报完成-----");
			transactionManageService.insertDateForMerchantDateFormInfo(date);
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

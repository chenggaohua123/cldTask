package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.model.DuplicateTransCount;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 定时执行每日重复订单标记
 * @author gaoyuan
 * */
public class DuplicateTransInfoFlag extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		int i=0;
		//获取没有标记的日期
		List<DuplicateTransCount> listDoDate=transactionManageService.queryNoFlagDate();
		for(DuplicateTransCount vo:listDoDate){
			//获取没有标记的流水号
			List<String> list=transactionManageService.queryNoFlagTradeNos(vo.getDoDate());
			for(String tradeNo:list){
				i++;
				if(i==100){
					break;
				}
				transactionManageService.updateTransInfoDupplicateFlag(tradeNo,vo.getDoDate());
			}
			if(i==100){
				break;
			}
		}
		
		if(i>0){
			getLogger().info("------"+"成功标记了:"+i+"笔记数 ！"+"------");
		}else{
			getLogger().info("没有要标记的记录！");
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

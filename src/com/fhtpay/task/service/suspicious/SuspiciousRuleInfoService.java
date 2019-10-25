package com.fhtpay.task.service.suspicious;

import java.util.List;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.suspicious.RulesInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerInfo;
import com.fhtpay.task.model.suspicious.SuspiciousMerTerInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.transaction.service.TransactionManageService;

public class SuspiciousRuleInfoService extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		try {
			getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup()+ "--------excu begin-----");
			transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		 	List<SuspiciousMerInfo> merInfoList = transactionManageService.querySuspiciousMerInfo();
			if (merInfoList == null || merInfoList.size() < 1) {
				getLogger().info("------没有商户信息------");
				return;
			}
			List<SuspiciousMerTerInfo> merTerInfoList = transactionManageService.querySuspiciousMerTerInfo();
			if (merTerInfoList == null || merTerInfoList.size() < 1) {
				getLogger().info("------没有商户终端信息------");
				return;
			}
			
			try{
			for(SuspiciousMerInfo merInfo : merInfoList){
				String info="";
				List<RulesInfo> ruleList = transactionManageService.queryRuleInfoByMerNo(merInfo.getMerNo());
				SuspiciousMerTerInfo susnfo = new SuspiciousMerTerInfo();
				susnfo.setMerNo(merInfo.getMerNo());
				if (null != ruleList && ruleList.size() > 0) {
					info= transactionManageService.startSuspiciousProcessClass(ruleList, susnfo, "2");
				}
				getLogger().info("------"+info+"------");
			}
			
			for(SuspiciousMerTerInfo merTerInfo : merTerInfoList){
				String info="";
				List<RulesInfo> ruleList = transactionManageService.queryRuleInfoByMerNoAndTerNo(merTerInfo.getMerNo(), merTerInfo.getTerNo());
				SuspiciousMerTerInfo susnfo = new SuspiciousMerTerInfo();
				susnfo.setMerNo(merTerInfo.getMerNo());
				susnfo.setTerNo(merTerInfo.getTerNo());
				if (null != ruleList && ruleList.size() > 0) {
					info= transactionManageService.startSuspiciousProcessClass(ruleList, susnfo, "3");
				}
				getLogger().info("------"+info+"------");
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

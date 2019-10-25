package com.fhtpay.task.service.emailservice.warn;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.warn.TransWarnEmailInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.transaction.service.TransactionManageService;

public class WarnEmailSend extends BaseTaskJobDetail {
	
	private static Map<String, Timestamp> sendCountLimit;

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		try{
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		List<TransWarnInfo> transWarnList = transactionManageService.queryTransWarnInfo();
		if(sendCountLimit==null){
			sendCountLimit = new HashMap<String, Timestamp>();
		}
		SendWarnEmailService emailService = (SendWarnEmailService) TaskMain.getInstance().getBeanFactory().getBean("sendTransWarnToUser");
		if(transWarnList!=null && transWarnList.size()>0){
			for(TransWarnInfo warn : transWarnList){
				List<TransWarnEmailInfo> emailList = transactionManageService.queryTransWarnEmailInfo(warn);
				if(emailList!=null && emailList.size()>0){
					emailService.sendEmail(emailList, warn, sendCountLimit);
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

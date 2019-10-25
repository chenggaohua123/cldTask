package com.fhtpay.task.service.messageservice;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.task.service.messageservice.util.FraudInfo;
import com.fhtpay.task.service.messageservice.util.FraudManage;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 黑卡库监控
 * @author gaoyuan
 *
 */
public class BSPServerMonitor extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	private static Map<String, Timestamp> sendCountLimit;
//	CommunicationsException
	@Override
	public void process(TaskInfo taskInfo) {
		try{
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		SendMessageService sendMessageService = (SendMessageService) TaskMain.getInstance().getBeanFactory().getBean("sendMessageToUser");
		List<TransWarnInfo> transWarnList = transactionManageService.queryTransWarnInfo();
		if(sendCountLimit==null){
			sendCountLimit = new HashMap<String, Timestamp>();
		}
		if(transWarnList!=null && transWarnList.size()>0){
			for(TransWarnInfo warn : transWarnList){
				sendMessageService.sendMessage(warn, sendCountLimit);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FraudInfo form=new FraudInfo();
		form.setEmail("test@test.com");
		FraudManage.queryRiskInfo(form);
		
		MessageSendImpl.sendLANZSMS(MessageSendImpl.LANZUserId, MessageSendImpl.LANZAccount, MessageSendImpl.LANZPassWord, "18872563282", "{黑卡库连接超时}", "", "", "");
	}

}

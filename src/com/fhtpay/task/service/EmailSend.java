package com.fhtpay.task.service;

import java.util.List;

import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.service.emailservice.SendEmailService;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 邮件发送定时任务
 * 
 * @author gaoyuan
 * */
public class EmailSend extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		List<EmailSendInfo> list=transactionManageService.queryEmailSendInfo();
		int count=0;
		for(EmailSendInfo info:list){
			SendEmailService sendEmailService=(SendEmailService) TaskMain.getInstance().getBeanFactory().getBean(info.getSendService());
			EmailInfo emailInfo=transactionManageService.queryEmailInfo();
			int i=sendEmailService.sendEmail(info,emailInfo);
			count+=i;
			info.setTerNo(emailInfo.getEmailAccount());//发送完成之后用以存储发送 的邮箱
			if(i>0){
				info.setStatus(1);
			}else{
				info.setStatus(2);
			}
			transactionManageService.updateEmailSendInfo(info);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		getLogger().info("-----------------------成功发送"+count+"封邮件----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
	}

}

package com.fhtpay.task.service.emailservice;

import java.util.HashMap;
import java.util.List;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.email.model.GwTradeRecord;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.Complaint;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;

public class SendSurveyToMerchantImpl implements SendEmailService{
	private EmailSendImpl emailSend;
	private TransactionManageDao transactionManageDao;
	public EmailSendImpl getEmailSend() {
		return emailSend;
	}

	public void setEmailSend(EmailSendImpl emailSend) {
		this.emailSend = emailSend;
	}

	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	@Override
	public int sendEmail(EmailSendInfo info,EmailInfo emailInfo) {
		EmailEntity entity=new EmailEntity();
		entity.setEmailHost(emailInfo.getEmailHost());
		entity.setEmailAcount(emailInfo.getEmailAccount());
		entity.setEmailPassword(emailInfo.getEmailPassword());
		entity.setEmailPort(emailInfo.getEmailPort());
		entity.setEmailSubject(info.getEmailSubject());
		GwTradeRecord result=transactionManageDao.queryTradeRocordByTradeNo(info);
		if(null == result){
			return 0;
		}
		if(null == result.getMerchantEmail() || "".equals(result.getMerchantEmail().trim())){
			return 0;
		}
		Complaint complain = transactionManageDao.queryComplaintTransByTradeNoAndComplainType(result.getTradeNo(), "0");
		if(null == complain){
			return 0;
		}
		entity.setTemplateName("survey_hooppay_merchant.ftl");
		
		HashMap<String, String> context=new HashMap<String, String>();
		context.put("tradeNo", info.getTradeNo());
		context.put("reason", complain.getComplaintTypeValue());
		context.put("merNo", result.getMerNo());
		context.put("tel", result.getTel());
		context.put("terNo", result.getTerNo());
		context.put("order", result.getOrderNo());
		context.put("deadline", complain.getDeadline());
		context.put("website", result.getWebsite());//铂赢奥管理后台-邮件管理-邮件发送附带信息
		entity.setEmailSendTo(result.getMerchantEmail());
		List<MerchantEmailConfigInfo> emailConfigs = transactionManageDao.queryMerEmailConfigByMerNoAndTerNo(result.getMerNo(), result.getTerNo(),"12");
		if(null==emailConfigs||emailConfigs.size()==0){
			emailSend.threadSendEmail(entity, context);
			return emailSend.updateEmailSendCountById(emailInfo);
		}
		for(MerchantEmailConfigInfo emailConfig:emailConfigs ){
			if(null != emailConfig && !"1".equals(emailConfig.getStatus())){
				return 0;
			}
			if(null != emailConfig && 
					"1".equals(emailConfig.getStatus()) && 
					null != emailConfig.getEmail() && 
					!"".equals(emailConfig.getEmail().trim())){
				entity.setEmailSendTo(emailConfig.getEmail());
			}
			emailSend.threadSendEmail(entity, context);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return emailSend.updateEmailSendCountById(emailInfo);
	
	}

}

package com.fhtpay.task.service.emailservice;

import java.util.HashMap;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantTerInfo;

public class SendMerchantRegEmailImpl implements SendEmailService{
	private TransactionManageDao transactionManageDao;
	private EmailSendImpl emailSend;
	
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
		//查询终端信息
		
	 	MerchantTerInfo terInfo = transactionManageDao.queryMerchantTerInfoByMerNoAndTerNo(info.getMerNo(), info.getTerNo());
		entity.setTemplateName("register_hooppay_merchant.ftl");
		entity.setEmailSendTo(terInfo.getEmail());
		HashMap<String, String> context=new HashMap<String, String>();
		context.put("merNo", terInfo.getMerNo());
		context.put("terNo", terInfo.getTerNo());
		context.put("MD5Key", terInfo.getShaKey());
		
		emailSend.threadSendEmail(entity, context);
		return emailSend.updateEmailSendCountById(emailInfo);
	
	}

}

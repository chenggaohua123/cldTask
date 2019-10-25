package com.fhtpay.task.service.emailservice;

import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;

public class SendReplyComplainToCardHolder implements SendEmailService {
	private EmailSendImpl emailSend;
	private TransactionManageDao transactionManageDao;
	
	
	
	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}


	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}
	
	
	public EmailSendImpl getEmailSend() {
		return emailSend;
	}


	public void setEmailSend(EmailSendImpl emailSend) {
		this.emailSend = emailSend;
	}


	@Override
	public int sendEmail(EmailSendInfo info,EmailInfo emailInfo) {
		// TODO Auto-generated method stub
		return 0;
	}
}

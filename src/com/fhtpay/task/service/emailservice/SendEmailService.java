package com.fhtpay.task.service.emailservice;

import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.transaction.model.EmailSendInfo;

public interface SendEmailService {
	public int sendEmail(EmailSendInfo info,EmailInfo emailInfo);
}

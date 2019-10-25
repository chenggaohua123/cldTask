package com.fhtpay.task.service.emailservice;

import java.util.HashMap;
import java.util.List;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.email.model.GwTradeRecord;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;

public class SendTransFailToCardHolder implements SendEmailService {
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
		EmailEntity entity=new EmailEntity();
		entity.setEmailHost(emailInfo.getEmailHost());
		entity.setEmailAcount(emailInfo.getEmailAccount());
		entity.setEmailPassword(emailInfo.getEmailPassword());
		entity.setEmailPort(emailInfo.getEmailPort());
		entity.setEmailSubject(info.getEmailSubject());
		
		GwTradeRecord result=transactionManageDao.queryTradeRocordByTradeNo(info);
		entity.setTemplateName("Fails_hooppay_holder.ftl");
		entity.setEmailSendTo(result.getCardHoldEmail());
		List<MerchantEmailConfigInfo> emailConfigs = transactionManageDao.queryMerEmailConfigByMerNoAndTerNo(result.getMerNo(), result.getTerNo(),"3");
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
		}
			HashMap<String, String> context=new HashMap<String, String>();
			context.put("email", result.getCardHoldEmail());
			context.put("tradeNo", info.getTradeNo());
			context.put("orderNo", result.getOrderNo());
			context.put("sourceAmount", result.getAmount());
			context.put("currency", result.getCurrency());
			context.put("website", result.getPayWebSite());
			context.put("tradeDateTime",result.getTransDate());
			context.put("tel", result.getTel());
			context.put("Fax", result.getFax());
			context.put("helpwebsite", result.getHelpWebsite());
			context.put("replayEmail", result.getReplyEmail());
			emailSend.threadSendEmail(entity, context);
		return emailSend.updateEmailSendCountById(emailInfo);
	}
}

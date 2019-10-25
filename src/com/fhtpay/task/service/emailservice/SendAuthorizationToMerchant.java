package com.fhtpay.task.service.emailservice;

import java.util.HashMap;
import java.util.List;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.email.model.GwTradeRecord;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;

public class SendAuthorizationToMerchant implements SendEmailService {
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
		entity.setTemplateName("sendAuthorizationToMerchant.ftl");
		entity.setEmailSendTo(result.getMerchantEmail());
		HashMap<String, String> context=new HashMap<String, String>();
		context.put("merNo", result.getMerNo());
		context.put("cardHolderEmail", result.getCardHoldEmail());
		context.put("TradeNo", info.getTradeNo());
		context.put("order", result.getOrderNo());
		context.put("terNo", result.getTerNo());
		context.put("sourceamount", result.getAmount());
		context.put("currency", result.getCurrency());
		context.put("tradeDatetime",result.getTransDate());
		context.put("merwebsite", result.getPayWebSite());
		context.put("acquirer", result.getAcquirer());
		context.put("tel", result.getTel());
		context.put("fax", result.getFax());
		context.put("helpwebsite", result.getHelpWebsite());
		context.put("replayEmail", result.getReplyEmail());
		List<MerchantEmailConfigInfo> emailConfigs = transactionManageDao.queryMerEmailConfigByMerNoAndTerNo(result.getMerNo(), result.getTerNo(),"2");
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

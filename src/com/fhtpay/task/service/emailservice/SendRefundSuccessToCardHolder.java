package com.fhtpay.task.service.emailservice;

import java.util.HashMap;
import java.util.List;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.email.model.GwTradeRecord;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;

public class SendRefundSuccessToCardHolder implements SendEmailService {
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
		entity.setTemplateName("refund_hooppay_holder.ftl");
		entity.setEmailSendTo(result.getCardHoldEmail());
		List<MerchantEmailConfigInfo> emailConfigs = transactionManageDao.queryMerEmailConfigByMerNoAndTerNo(result.getMerNo(), result.getTerNo(),"5");
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
			context.put("TradeNo", info.getTradeNo());
			context.put("order", result.getOrderNo());
			context.put("email", result.getCardHoldEmail());
			context.put("amount", result.getAmount());
			context.put("Currency", result.getCurrency());
			context.put("merwebsite", result.getPayWebSite());
			context.put("tradeDateTime",result.getTransDate());
			context.put("iew_refundamount", result.getRefundAmount());
			context.put("view_currency", result.getCurrency());
			context.put("acquirer", "");
			context.put("tel", result.getTel());
			context.put("Fax", result.getFax());
			context.put("helpwebsite", result.getHelpWebsite());
			context.put("replayEmail", result.getReplyEmail());
			context.put("contactEmail", result.getReplyEmail());	
			emailSend.threadSendEmail(entity, context);
		return emailSend.updateEmailSendCountById(emailInfo);
	}

}

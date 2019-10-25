package com.fhtpay.task.service.emailservice;

import java.util.HashMap;
import java.util.List;

import com.fhtpay.common.SHA256Utils;
import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.email.model.GwTradeRecord;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;


public class SendTransSuccessToCardHolder implements SendEmailService {
	private EmailSendImpl emailSend;
	private TransactionManageDao transactionManageDao;
	
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
		entity.setTemplateName("succeed_hooppay_holder.ftl");
		if("405888".equals(result.getMerNo())||"1888".equals(result.getMerNo())){
			entity.setTemplateName("succeed_hooppay_holder_JP.ftl");
		}
		entity.setEmailSendTo(result.getCardHoldEmail());
		List<MerchantEmailConfigInfo> emailConfigs = transactionManageDao.queryMerEmailConfigByMerNoAndTerNo(result.getMerNo(), result.getTerNo(),"1");
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
			context.put("tradeNo", info.getTradeNo());
			context.put("order", result.getOrderNo());
			context.put("email", result.getCardHoldEmail());
			context.put("sourceamount", result.getAmount());
			context.put("currency", result.getCurrency());
			context.put("merwebsite", result.getPayWebSite());
			context.put("randomQueryCode", "");
			context.put("tradeDateTime",result.getTransDate());
			context.put("acquirer", result.getAcquirer());
			context.put("tel", result.getTel());
			context.put("fax", result.getFax());
			context.put("helpWebsite", result.getHelpWebsite());
			context.put("replayEmail", result.getReplyEmail());
			context.put("randomCode", SHA256Utils.getSHA256Encryption(info.getTradeNo()));
			emailSend.threadSendEmail(entity, context);
		return emailSend.updateEmailSendCountById(emailInfo);
	}
	public EmailSendImpl getEmailSend() {
		return emailSend;
	}
	public void setEmailSend(EmailSendImpl emailSend) {
		this.emailSend = emailSend;
	}
	
	
}

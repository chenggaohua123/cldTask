package com.fhtpay.task.service.emailservice;

import java.util.HashMap;
import java.util.List;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.task.model.email.RiskTransInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;
import com.fhtpay.transaction.model.EmailSendInfo;
import com.fhtpay.transaction.model.MerchantEmailConfigInfo;

public class SendPayPendingToCardHolderImpl implements SendEmailService {

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
		
		entity.setTemplateName("risk_trans_merchant.flt");
		RiskTransInfo riskInfo = transactionManageDao.queryMerchantRiskTransInfo(info.getTradeNo());
		if(riskInfo == null){
			return 0;
		}
		entity.setEmailSendTo(riskInfo.getEmail());
		System.out.println("---------------" + riskInfo.getEmail());
		HashMap<String, String> riskContext = new HashMap<String, String>();
		riskContext.put("merNo", riskInfo.getMerNo());
		riskContext.put("terNo", riskInfo.getTerNo());
		riskContext.put("tradeNo", riskInfo.getTradeNo());
		riskContext.put("orderNo", riskInfo.getOrderNo());
		riskContext.put("doReason",
				riskInfo.getDoReason() != null ? riskInfo.getDoReason() : "");
		riskContext.put("payWebSite", riskInfo.getWebsite());
		riskContext.put("merchantName", riskInfo.getMerchantName());
		riskContext.put("transDate", riskInfo.getTransDate());
		riskContext.put("merTransAmount", riskInfo.getMerTransAmount());
		riskContext.put("merBusCurrency", riskInfo.getMerBusCurrency());
//		System.out.println("风险订单数据---"+ristList.size());
		List<MerchantEmailConfigInfo> emailConfigs = transactionManageDao.queryMerEmailConfigByMerNoAndTerNo(riskInfo.getMerNo(), riskInfo.getTerNo(),"11");
		if(null==emailConfigs||emailConfigs.size()==0){
			emailSend.threadSendEmail(entity, riskContext);
		}
		for(MerchantEmailConfigInfo emailConfig:emailConfigs ){
			if(null != emailConfig && !"1".equals(emailConfig.getStatus())){
				return 0;
			}
			if (riskInfo.getEmail() != null && !"".equals(riskInfo.getEmail())) {
				entity.setEmailSendTo(riskInfo.getEmail());
			}
			if (null != emailConfig && "1".equals(emailConfig.getStatus())
					&& null != emailConfig.getEmail()
					&& !"".equals(emailConfig.getEmail().trim())) {
				entity.setEmailSendTo(emailConfig.getEmail());
			}
			
			emailSend.threadSendEmail(entity, riskContext);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return emailSend.updateEmailSendCountById(emailInfo);
	}

}

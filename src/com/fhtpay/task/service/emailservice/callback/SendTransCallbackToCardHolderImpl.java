package com.fhtpay.task.service.emailservice.callback;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.task.model.callback.TransCallbackInfo;
import com.fhtpay.task.model.callback.TransGoodsInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class SendTransCallbackToCardHolderImpl implements
		SendTransCallbackToCardHolder {
	
	private static Log logger = LogFactory.getLog(SendTransCallbackToCardHolderImpl.class);
	
	private TransactionManageDao transactionManageDao;
	
	private EmailSendImpl callbackEmailSend;

	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	public EmailSendImpl getCallbackEmailSend() {
		return callbackEmailSend;
	}

	public void setCallbackEmailSend(EmailSendImpl callbackEmailSend) {
		this.callbackEmailSend = callbackEmailSend;
	}
	
	@Override
	public int sendTransCallbackInfoToMerchant(TransCallbackInfo info)
			throws InterruptedException {
		List<TransGoodsInfo> goods = transactionManageDao.queryTransGoodsInfoList(info.getTradeNo());
		StringBuffer sb = new StringBuffer();
		if(goods!=null && goods.size()>0){
			for(TransGoodsInfo good : goods){
				if(good!=null){
					sb.append(good.getGoodsName()!=null?good.getGoodsName():"")
					.append(",&nbsp;&nbsp;")
					.append(good.getQuantity()!=null?good.getQuantity():"")
					.append("&nbsp;&nbsp;")
					.append(",")
					.append(info.getCurrency()!=null?info.getCurrency():"")
					.append("&nbsp;")
					.append(good.getGoodsPrice()!=null?(new BigDecimal(good.getGoodsPrice()).setScale(2, BigDecimal.ROUND_HALF_UP)):"")//四舍五入保留两位小数
					.append("<br/>");
				}
			}
		}
		EmailInfo emailInfo = callbackEmailSend.queryEmailInfoByEmail(info
				.getSendEmail());
		if(emailInfo!=null){
			EmailEntity entity = new EmailEntity();
			entity.setEmailHost(emailInfo.getEmailHost()!=null?emailInfo.getEmailHost():"");
			entity.setEmailAcount(emailInfo.getEmailAccount()!=null?emailInfo.getEmailAccount():"");
			entity.setEmailPassword(emailInfo.getEmailPassword()!=null?emailInfo.getEmailPassword():"");
			entity.setEmailPort(emailInfo.getEmailPort()!=null?emailInfo.getEmailPort():"");
			HashMap<String, String> context = new HashMap<String, String>();
			if ("1".equals(info.getEmailModel())) {//1.虚拟商户持卡人回访模版
				entity.setEmailSubject("Order Confirmation");
				context.put("merchandise", sb.toString());
				entity.setTemplateName("trans_callback_virtual.flt");
			} else if ("2".equals(info.getEmailModel())) {//2.正品商户持卡人回访模版
				entity.setEmailSubject("Order Confirmation");
				context.put("merchandise", sb.toString());
				context.put("trackingNo", info.getTrackingNO()!=null?info.getTrackingNO():"");
				context.put("agency", info.getAgency()!=null?info.getAgency():"");
				entity.setTemplateName("trans_callback_certified.flt");
			} else if ("3".equals(info.getEmailModel())){//3.拒付订单持卡人回访模版
				entity.setEmailSubject("Chargeback Review");
				context.put("merchandise", sb.toString());
				entity.setTemplateName("trans_callback_chargeback.flt");
			}
			else if ("4".equals(info.getEmailModel())){//4.日语持卡人回访模版
				entity.setEmailSubject("注文の確認");
				context.put("trackingNo", info.getTrackingNO()!=null?info.getTrackingNO():"");
				context.put("agency", info.getAgency()!=null?info.getAgency():"");
				context.put("merchandise", sb.toString());
				entity.setTemplateName("trans_callback_certified_jp.flt");
			}else if ("5".equals(info.getEmailModel())){
				entity.setEmailSubject("Order from deals3c.com");
				context.put("trackingNo", info.getTrackingNO()!=null?info.getTrackingNO():"");
				context.put("agency", info.getAgency()!=null?info.getAgency():"");
				context.put("merchandise", sb.toString());
				entity.setTemplateName("Order_from_deals3c.com.flt");
			}else if ("6".equals(info.getEmailModel())){//6.社交网站持卡人回访模板
				entity.setEmailSubject("Order Confirmation");
				entity.setTemplateName("trans_callback_virtual_CN.flt");
			}
			entity.setEmailSendTo(info.getEmail());
			context.put("tradeNo", info.getTradeNo()!=null?info.getTradeNo():"");
			context.put("orderNo", info.getOrderNo()!=null?info.getOrderNo():"");
			context.put("website", info.getWebsite()!=null?info.getWebsite():"");
			context.put("transDate", info.getTransDate()!=null?info.getTransDate():"");
			context.put("amount", info.getAmount()!=null?info.getAmount():"");
			context.put("currency", info.getCurrency()!=null?info.getCurrency():"");
			context.put("name", info.getName()!=null?info.getName():"");
			Thread.currentThread().sleep(2000);
			try{
				callbackEmailSend.threadSendEmail(entity, context, info.getId());
			}catch(Exception e){
				logger.info(info.getTradeNo()+"发送持卡人回访邮件失败：异常:"+e.getStackTrace());
				e.getStackTrace();
				transactionManageDao.updateTransCallbackInfo(info.getId(), 1);
				return 0;
			}
			transactionManageDao.updateTransCallbackInfo(info.getId(), 2);
			return callbackEmailSend.updateEmailSendCountById(emailInfo);
		}
		return 0;
	}

}

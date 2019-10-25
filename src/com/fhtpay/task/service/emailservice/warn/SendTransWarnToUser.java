package com.fhtpay.task.service.emailservice.warn;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.email.model.EmailEntity;
import com.fhtpay.email.model.EmailInfo;
import com.fhtpay.task.model.warn.TransWarnEmailInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.model.warn.TransWarnMessagInfo;
import com.fhtpay.task.service.emailservice.EmailSendImpl;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class SendTransWarnToUser implements SendWarnEmailService {
	
	private static Log logger = LogFactory.getLog(SendTransWarnToUser.class);
	
	private TransactionManageDao transactionManageDao;
	
	private EmailSendImpl emailSend;

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
	public int sendEmail(List<TransWarnEmailInfo> emailList, TransWarnInfo warn, Map<String, Timestamp> sendCountLimit) {
		
//		List<BankCurrencyInfo> currencyList = transactionManageDao.queryBankCurrencyInfo(warn.getBankId());
//		if(!(currencyList!=null) || !(currencyList.size()>0)){
//			return 0;
//		}
		TransWarnMessagInfo message = transactionManageDao.queryTransWarnMessageInfoByWarnId(warn.getId());
		if(null==message){
			logger.info("没有信息内容,获取默认信息");
			EmmailUtils defaultMsg = new EmmailUtils();
			message = new TransWarnMessagInfo();
			message.setMessage(defaultMsg.getDefaultMessage(warn));
		}
		logger.info("发送的信息内容:"+message.getMessage());
		
		Timestamp preSendTime = sendCountLimit.get(warn.getId());
		if(null == preSendTime){
			logger.info("第一次发送时间:"+new Date().toLocaleString());
			sendCountLimit.put(warn.getId(), new Timestamp(System.currentTimeMillis()));
		}else{
			long ms = new Timestamp(System.currentTimeMillis()).getTime()-preSendTime.getTime();
			if(!(ms>=1000L*60*Integer.parseInt(warn.getCycle()))){
				logger.info("距离上次发送邮件的时间没有超过:"+warn.getCycle()+"分钟,不发送");
				return 0;
			}
			logger.info("距离上次发送邮件的时间超过:"+warn.getCycle()+"分钟,继续发送");
			sendCountLimit.put(warn.getId(), new Timestamp(System.currentTimeMillis()));
		}
		
		
//		for(BankCurrencyInfo currency : currencyList){
				if("0".equals(warn.getType())){
					int count = transactionManageDao.queryRespMsgTransInfo(warn, "0");
					if(count>=Integer.parseInt(warn.getTime())){
						if(emailList!=null && emailList.size()>0){
							for(TransWarnEmailInfo info : emailList){
								EmailInfo emailInfo=emailSend.queryEmailInfo();
								EmailEntity entity=new EmailEntity();
								entity.setEmailHost(emailInfo.getEmailHost());
								entity.setEmailAcount(emailInfo.getEmailAccount());
								entity.setEmailPassword(emailInfo.getEmailPassword());
								entity.setEmailPort(emailInfo.getEmailPort());
								entity.setEmailSubject("铂赢奥交易预警信息");
								entity.setTemplateName("trans_warn_respMsg.ftl");
								EmmailUtils utils = new EmmailUtils();
								warn.setTime(new Integer(count).toString());
								String con = utils.getMessage(message.getMessage(), warn, null);
								entity.setEmailSendTo(info.getEmail());
								logger.info("发送邮箱:"+info.getEmail());
								HashMap<String, String> context=new HashMap<String, String>();
								context.put("warnInfo", con);
								context.put("userNme", info.getUserName());
//								context.put("bankName", warn.getBankName());
//								context.put("activeTime", warn.getActiveTime());
//								context.put("time", warn.getTime());
//								context.put("respMsg", warn.getRespMsg());
//								context.put("opTime", format.format(new Date()));
								//蓝付-交易预警:${bankName}在${activeTime}分钟内已有超过${time}笔订单因${respMsg}交易失败了,异常时间:${opTime}
								logger.info("同一返回信息发送内容:"+con);
								emailSend.threadSendEmail(entity, context);
							}
						}
					}
				}
				if("1".equals(warn.getType())){
					int count = 0;
					String transId = transactionManageDao.queryFailedTransInfo(warn, "0");
					if(transId!=null && !("".equals(transId))){
						count+=1;
					}else{
						return 0;
					}
					List<String> list = transactionManageDao.queryFaildTransRespCodeInfo(warn, "0", transId, new Integer(Integer.parseInt(warn.getTime())-1));
					if(list!=null && list.size()>0){
						for(String code : list){
							if(code!=null && !("".equals(code))){
								if(!"00".equals(code)){
									count+=1;
								}
							}
						}
					}
					if(count>=Integer.parseInt(warn.getTime())){
						if(emailList!=null && emailList.size()>0){
							for(TransWarnEmailInfo info : emailList){
								EmailInfo emailInfo=emailSend.queryEmailInfo();
								EmailEntity entity=new EmailEntity();
								entity.setEmailHost(emailInfo.getEmailHost());
								entity.setEmailAcount(emailInfo.getEmailAccount());
								entity.setEmailPassword(emailInfo.getEmailPassword());
								entity.setEmailPort(emailInfo.getEmailPort());
								entity.setEmailSubject("铂赢奥交易预警信息");
								entity.setTemplateName("trans_warn_failed.ftl");
								EmmailUtils utils = new EmmailUtils();
								warn.setTime(new Integer(count).toString());
								String con = utils.getMessage(message.getMessage(), warn, null);
								entity.setEmailSendTo(info.getEmail());
								logger.info("发送邮箱:"+info.getEmail());
								HashMap<String, String> context=new HashMap<String, String>();
								context.put("warnInfo", con);
								context.put("userNme", info.getUserName());
//								context.put("bankName", warn.getBankName());
//								context.put("activeTime", warn.getActiveTime());
//								context.put("time", warn.getTime());
//								context.put("opTime", format.format(new Date()));
								//蓝付-交易预警:${bankName}在${activeTime}分钟内已有超过${time}笔订单连续交易失败了,异常时间:${opTime}
								logger.info("连续交易失败发送内容:"+con);
								emailSend.threadSendEmail(entity, context);
							}
						}
					}
				}
				if("2".equals(warn.getType())){
					int count = transactionManageDao.queryNoTransInfo(warn, "0");
					if(count==0){
						if(emailList!=null && emailList.size()>0){
							for(TransWarnEmailInfo info : emailList){
								EmailInfo emailInfo=emailSend.queryEmailInfo();
								EmailEntity entity=new EmailEntity();
								entity.setEmailHost(emailInfo.getEmailHost());
								entity.setEmailAcount(emailInfo.getEmailAccount());
								entity.setEmailPassword(emailInfo.getEmailPassword());
								entity.setEmailPort(emailInfo.getEmailPort());
								entity.setEmailSubject("铂赢奥交易预警信息");
								entity.setTemplateName("trans_warn_noTrans.ftl");
								EmmailUtils utils = new EmmailUtils();
								warn.setTime(new Integer(count).toString());
								String con = utils.getMessage(message.getMessage(), warn, null);
								entity.setEmailSendTo(info.getEmail());
								logger.info("发送邮箱:"+info.getEmail());
								HashMap<String, String> context=new HashMap<String, String>();
								context.put("warnInfo", con);
								context.put("userNme", info.getUserName());
//								context.put("bankName", warn.getBankName());
//								context.put("activeTime", warn.getActiveTime());
//								context.put("opTime", format.format(new Date()));
								//蓝付-交易预警:${bankName}已有${activeTime}分钟没有订单了,异常时间:${opTime}
								logger.info("没有交易发送内容:"+con);
								emailSend.threadSendEmail(entity, context);
							}
						}
					}
				}
				if("3".equals(warn.getType())){
					int count = transactionManageDao.queryRiskTransInfo(warn, "0");
					if(count>=Integer.parseInt(warn.getTime())){
						if(emailList!=null && emailList.size()>0){
							for(TransWarnEmailInfo info : emailList){
								EmailInfo emailInfo=emailSend.queryEmailInfo();
								EmailEntity entity=new EmailEntity();
								entity.setEmailHost(emailInfo.getEmailHost());
								entity.setEmailAcount(emailInfo.getEmailAccount());
								entity.setEmailPassword(emailInfo.getEmailPassword());
								entity.setEmailPort(emailInfo.getEmailPort());
								entity.setEmailSubject("铂赢奥交易预警信息");
								entity.setTemplateName("trans_warn_riskTrans.ftl");
								EmmailUtils utils = new EmmailUtils();
								warn.setTime(new Integer(count).toString());
								String con = utils.getMessage(message.getMessage(), warn, null);
								entity.setEmailSendTo(info.getEmail());
								logger.info("发送邮箱:"+info.getEmail());
								HashMap<String, String> context=new HashMap<String, String>();
								context.put("warnInfo", con);
								context.put("userNme", info.getUserName());
//								context.put("bankName", warn.getBankName());
//								context.put("activeTime", warn.getActiveTime());
//								context.put("opTime", format.format(new Date()));
								//蓝付-交易预警:${bankName}已有${activeTime}分钟没有订单了,异常时间:${opTime}
								logger.info("没有交易发送内容:"+con);
								emailSend.threadSendEmail(entity, context);
							}
						}
					}
				}
//		}
		
		return 0;
	}

}

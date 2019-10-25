package com.fhtpay.task.service.messageservice;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.task.model.warn.MessageInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;
import com.fhtpay.task.model.warn.TransWarnMessagInfo;
import com.fhtpay.task.model.warn.TransWarnPhoneInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class SendWarnMessageToUser implements SendMessageService {
	
	private static Log logger = LogFactory.getLog(SendWarnMessageToUser.class);

	private MessageSendImpl messageSend;
	
	private TransactionManageDao transactionManageDao;

	public MessageSendImpl getMessageSend() {
		return messageSend;
	}

	public void setMessageSend(MessageSendImpl messageSend) {
		this.messageSend = messageSend;
	}
	
	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	@Override
	public void sendMessage(TransWarnInfo warnInfo, Map<String, Timestamp> sendCountLimit) {
		TransWarnPhoneInfo phoneList = transactionManageDao.queryTransWarnPhoneInfo(warnInfo.getId());
		if(!(phoneList!=null)){
			logger.info("交易预警接收信息电话信息null");
			return;
		}
		if(!(phoneList.getPhoneNo()!=null) || "".equals(phoneList.getPhoneNo())){
			logger.info("交易预警接受信息电话信息为空");
			return;
		}
		TransWarnMessagInfo message = transactionManageDao.queryTransWarnMessageInfoByWarnId(warnInfo.getId());
		if(null==message){
			logger.info("没有短息内容");
			message = new TransWarnMessagInfo();
			message.setMessage(messageSend.getDefaultMessage(warnInfo));
		}
		phoneList.setPhoneNo(phoneList.getPhoneNo().replace(",", ";"));
		logger.info("查找到的电话号码:"+phoneList.getPhoneNo());
		
		Timestamp preSendTime = sendCountLimit.get(warnInfo.getId());
		if(null == preSendTime){
			logger.info("第一次发送时间:"+new Date().toLocaleString());
			sendCountLimit.put(warnInfo.getId(), new Timestamp(System.currentTimeMillis()));
		}else{
			long ms = new Timestamp(System.currentTimeMillis()).getTime()-preSendTime.getTime();
			if(!(ms>=1000L*60*Integer.parseInt(warnInfo.getCycle()))){
				logger.info("距离上次发送邮件的时间没有超过:"+warnInfo.getCycle()+"分钟,不发送");
				return ;
			}
			logger.info("距离上次发送邮件的时间超过:"+warnInfo.getCycle()+"分钟,继续发送");
			sendCountLimit.put(warnInfo.getId(), new Timestamp(System.currentTimeMillis()));
		}
		
		if("0".equals(warnInfo.getType())){
			logger.info("交易返回信息：失败原因内容、出现次数");
			int count = transactionManageDao.queryRespMsgTransInfo(warnInfo, "0");
			logger.info("规则次数:"+warnInfo.getTime()+",查询次数:"+count);
			if(count>=Integer.parseInt(warnInfo.getTime())){
				MessageInfo messageInfo = new MessageInfo();
				warnInfo.setTime(new Integer(count).toString());
				String content = messageSend.getMessage(message.getMessage(), warnInfo, null);
				messageInfo.setContent(content);
				logger.info("发送消息内容:"+content);
				messageInfo.setMobile(phoneList.getPhoneNo());
				logger.info("发送电话号码:"+messageInfo.getMobile());
				messageSend.threadSendMessage(messageInfo);
			}
		}
		if("1".equals(warnInfo.getType())){
			logger.info("连续交易失败：失败笔数");
			int count = 0;
			String transId = transactionManageDao.queryFailedTransInfo(warnInfo, "0");
			if(transId!=null && !("".equals(transId))){
				count+=1;
			}else{
				return;
			}
			List<String> list = transactionManageDao.queryFaildTransRespCodeInfo(warnInfo, "0", transId, new Integer(Integer.parseInt(warnInfo.getTime())-1));
			if(list!=null && list.size()>0){
				for(String code : list){
					if(code!=null && !("".equals(code))){
						if(!"00".equals(code)){
							count+=1;
						}
					}
				}
			}
			logger.info("规则次数"+warnInfo.getTime()+"查询次数:"+count);
			if(count>=Integer.parseInt(warnInfo.getTime())){
				MessageInfo messageInfo = new MessageInfo();
				warnInfo.setTime(new Integer(count).toString());
				String content = messageSend.getMessage(message.getMessage(), warnInfo, null);
				logger.info("发送消息内容:"+content);
				messageInfo.setContent(content.toString());
				messageInfo.setMobile(phoneList.getPhoneNo());
				logger.info("发送电话号码:"+messageInfo.getMobile());
				messageSend.threadSendMessage(messageInfo);
			}
		}
		if("2".equals(warnInfo.getType())){
			logger.info("无新增交易");
			int count = transactionManageDao.queryNoTransInfo(warnInfo, "0");
			logger.info("查询次数:"+count);
			if(count==0){
				MessageInfo messageInfo = new MessageInfo();
				warnInfo.setTime(new Integer(count).toString());
				String content = messageSend.getMessage(message.getMessage(), warnInfo, null);
				logger.info("发送消息内容:"+content);
				messageInfo.setContent(content.toString());
				messageInfo.setMobile(phoneList.getPhoneNo());
				logger.info("发送电话号码:"+messageInfo.getMobile());
				messageSend.threadSendMessage(messageInfo);
			}
		}
		if("3".equals(warnInfo.getType())){
			int count = transactionManageDao.queryRiskTransInfo(warnInfo, "0");
			if(count>=Integer.parseInt(warnInfo.getTime())){
				MessageInfo messageInfo = new MessageInfo();
				warnInfo.setTime(new Integer(count).toString());
				String content = messageSend.getMessage(message.getMessage(), warnInfo, null);
				logger.info("发送消息内容:"+content);
				messageInfo.setContent(content.toString());
				messageInfo.setMobile(phoneList.getPhoneNo());
				logger.info("发送电话号码:"+messageInfo.getMobile());
				messageSend.threadSendMessage(messageInfo);
			}
		}
	}

}

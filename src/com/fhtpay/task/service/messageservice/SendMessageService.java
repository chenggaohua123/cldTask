package com.fhtpay.task.service.messageservice;

import java.sql.Timestamp;
import java.util.Map;

import com.fhtpay.task.model.warn.TransWarnInfo;


public interface SendMessageService {

	/**
	 * 发送短信
	 */
	public void sendMessage(TransWarnInfo warnInfo, Map<String, Timestamp> sendCountLimit);
}

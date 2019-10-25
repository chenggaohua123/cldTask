package com.fhtpay.task.service.emailservice.warn;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.fhtpay.task.model.warn.TransWarnEmailInfo;
import com.fhtpay.task.model.warn.TransWarnInfo;

public interface SendWarnEmailService {
	
	public int sendEmail(List<TransWarnEmailInfo> emailList, TransWarnInfo warn, Map<String, Timestamp> sendCountLimit);
}

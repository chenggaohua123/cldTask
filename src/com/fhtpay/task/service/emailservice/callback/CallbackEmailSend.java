package com.fhtpay.task.service.emailservice.callback;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.callback.TransCallbackInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.transaction.service.TransactionManageService;

public class CallbackEmailSend extends BaseTaskJobDetail {
	
	private static Log logger = LogFactory.getLog(CallbackEmailSend.class);

	private TransactionManageService transactionManageService;
	
	private SendTransCallbackToCardHolder sendTransCallbackToMerchant;
	
	@Override
	public void process(TaskInfo taskInfo) {
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		sendTransCallbackToMerchant = (SendTransCallbackToCardHolder) TaskMain.getInstance().getBeanFactory().getBean("sendTransCallbackToMerchant");
		List<TransCallbackInfo> list = transactionManageService.queryTransCallbackInfoList();
		if(list!=null && list.size()>0){
			for(TransCallbackInfo info : list){
				if(info!=null){
					if(info.getEmail()!=null && !("".equals(info.getEmail()))){
						try {
							sendTransCallbackToMerchant.sendTransCallbackInfoToMerchant(info);
						} catch (Exception e) {
							logger.info("执行发送回访邮件信息失败,失败订单流水号:"+info.getTradeNo()+",异常信息:"+e.getStackTrace());
						}
					}
				}
			}
		}
	}

}

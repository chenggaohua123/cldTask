package com.fhtpay.task.service.emailservice.callback;

import com.fhtpay.task.model.callback.TransCallbackInfo;

public interface SendTransCallbackToCardHolder {
	
	public int sendTransCallbackInfoToMerchant(TransCallbackInfo info) throws InterruptedException;
	
}

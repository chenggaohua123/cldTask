package com.fhtpay.task.service.refundReturn;

import java.util.List;

import com.fhtpay.task.model.refundReturn.RefundConfigInfo;

public interface RefundReturnService {

	/**
	 * 链接退款返回配置信息
	 */
	public int returnRefundMessage(List<RefundConfigInfo> list);
	
}

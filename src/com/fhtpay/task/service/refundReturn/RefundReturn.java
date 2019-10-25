package com.fhtpay.task.service.refundReturn;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.refundReturn.RefundConfigInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.transaction.service.TransactionManageService;

public class RefundReturn extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	private RefundReturnService refundReturnToUser;
	
	@Override
	public void process(TaskInfo taskInfo) {
		try{
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		refundReturnToUser = (RefundReturnService) TaskMain.getInstance().getBeanFactory().getBean("refundReturnToUser");
		List<RefundConfigInfo> list = transactionManageService.queryRefundConfigInfoList();
		if(list!=null && list.size()>0){
			refundReturnToUser.returnRefundMessage(list);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}

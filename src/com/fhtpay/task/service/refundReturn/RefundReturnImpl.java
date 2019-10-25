package com.fhtpay.task.service.refundReturn;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.annotation.Transactional;

import com.fhtpay.task.model.refundReturn.RefundConfigInfo;
import com.fhtpay.task.model.refundReturn.RefundTransInfo;
import com.fhtpay.transaction.service.TransactionManageService;

public class RefundReturnImpl {

	private static Log logger = LogFactory.getLog(RefundReturnImpl.class);
	
	private TaskExecutor taskExecutor;
	
	private TransactionManageService transactionManageService;

	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public TransactionManageService getTransactionManageService() {
		return transactionManageService;
	}

	public void setTransactionManageService(
			TransactionManageService transactionManageService) {
		this.transactionManageService = transactionManageService;
	}

	public void startRefundReturn(final RefundConfigInfo config, final RefundTransInfo trans){
		taskExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					RefundReturn(config, trans);
				} catch (ClientProtocolException e) {
					logger.info("HTTP链接异常:"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					logger.info("IO异常:"+e.getMessage());
					e.printStackTrace();
				} catch (Exception e) {
					logger.info("异常:"+e.getMessage());
					e.printStackTrace();
				}
			}
		});
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void RefundReturn(RefundConfigInfo config, RefundTransInfo trans) throws ClientProtocolException, IOException{
		StringBuffer sb = new StringBuffer();
		sb.append(config.getConfigURL()).append("?tradeNo=").append(trans.getTradeNo()).append("&respCode=").append(trans.getRespCode()).append("&respMsg=")
		.append(trans.getRespCode()!=null?("0".equals(trans.getRespCode())?"Pending":("1".equals(trans.getRespCode())?"RefundCancellation":("2".equals(trans.getRespCode())?"Refunded":("4".equals(trans.getRespCode())?"RefundFailure":"")))):"");
		logger.info("返回地址:"+sb.toString());
		int a = transactionManageService.updateRefundInfoReturnFlag(config, trans, sb.toString());
		if(a>0){
			logger.info(sb.toString()+"->返回成功");
		}else{
			logger.info(sb.toString()+"->返回失败");
		}
	}
	
}

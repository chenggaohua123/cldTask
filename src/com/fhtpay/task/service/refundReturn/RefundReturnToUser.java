package com.fhtpay.task.service.refundReturn;

import java.util.List;

import com.fhtpay.task.model.refundReturn.RefundConfigInfo;
import com.fhtpay.task.model.refundReturn.RefundTransInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class RefundReturnToUser implements RefundReturnService {
	
	private TransactionManageDao transactionManageDao;
	
	private RefundReturnImpl refundReturnImpl;

	@Override
	public int returnRefundMessage(List<RefundConfigInfo> list) {
		for(RefundConfigInfo config : list){
			if(config.getConfigURL()!=null && !("".equals(config.getConfigURL()))){
				List<RefundTransInfo> transList = transactionManageDao.queryRefundTransInfo(config.getMerNo(), config.getTerNo(), 1, 0);
				if(transList!=null && transList.size()>0){
					for(RefundTransInfo trans : transList){
						try {
							Thread.sleep(500);
							refundReturnImpl.startRefundReturn(config, trans);
						} catch (InterruptedException e) {
							System.out.println("线程休眠异常:"+e.getMessage());
						}
					}
				}
			}
		}
		return 0;
	}

	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	public RefundReturnImpl getRefundReturnImpl() {
		return refundReturnImpl;
	}

	public void setRefundReturnImpl(RefundReturnImpl refundReturnImpl) {
		this.refundReturnImpl = refundReturnImpl;
	}

}

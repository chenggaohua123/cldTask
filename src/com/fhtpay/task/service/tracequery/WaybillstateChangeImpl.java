package com.fhtpay.task.service.tracequery;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.task.model.trace.MerchantInfo;
import com.fhtpay.task.model.trace.MerchantTraceInfo;
import com.fhtpay.task.model.trace.TraceCountryInfo;
import com.fhtpay.task.model.trace.ems.DetailList;
import com.fhtpay.task.model.trace.ems.EMSTraceInfo;
import com.fhtpay.transaction.dao.TransactionManageDao;

public class WaybillstateChangeImpl implements WaybillstateChange {
	
	private static Log logger = LogFactory.getLog(WaybillstateChangeImpl.class);
	
	private QueryTraceServiceImpl queryTraceService;
	
	private TransactionManageDao transactionManageDao;

	public QueryTraceServiceImpl getQueryTraceService() {
		return queryTraceService;
	}

	public void setQueryTraceService(QueryTraceServiceImpl queryTraceService) {
		this.queryTraceService = queryTraceService;
	}

	public TransactionManageDao getTransactionManageDao() {
		return transactionManageDao;
	}

	public void setTransactionManageDao(TransactionManageDao transactionManageDao) {
		this.transactionManageDao = transactionManageDao;
	}

	@Override
	public void upateEMSGoodspressOperationStatus() {
		List<MerchantInfo> merchants = transactionManageDao.queryMerchantInfoList();
		if(merchants!=null && merchants.size()>0){
			for(MerchantInfo merchant : merchants){
				List<MerchantTraceInfo> list = transactionManageDao.queryMerchantTraceInfoList("EMS", merchant.getMerNo());
				if (list != null && list.size() > 0) {
					for (MerchantTraceInfo trace : list) {
						MerchantTraceInfo agin = updateGoodsOperationStatusInfo(trace);
						if(agin!=null){
							logger.info(agin.getTrackNo()+"运单开始第二次查询");
							MerchantTraceInfo thrid = updateGoodsOperationStatusInfo(agin);
							if(thrid!=null){
								logger.info(thrid.getTrackNo()+"运单查询失败");
							}else{
								logger.info(agin.getTrackNo()+"第二次查询成功");
							}
						}else{
							logger.info(trace.getTrackNo()+"第一次查询成功");
						}
					}
				}
			}
		}
	}
	
	public MerchantTraceInfo updateGoodsOperationStatusInfo(MerchantTraceInfo trace){
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			logger.info("查询运单线程休眠异常");
		}
		try {
			EMSTraceInfo ems = queryTraceService.queryEMSTraceInfo(trace.getTrackNo());
			if (!(ems != null)) {
				logger.info(trace.getTrackNo()+"查询出的运单信息为空");
				return trace;
			}
			if (!(ems.getMailNo() != null) || "".equals(ems.getMailNo())) {
				logger.info("查询返回运单号为空,本地运单号为:"+trace.getTrackNo());
				return trace;
			}
			if (!trace.getTrackNo().equals(ems.getMailNo())) {
				logger.info("运单号不符,本地运单号为:"+trace.getTrackNo()+",查询结果返回运单号为:"+ems.getMailNo());
				return null;
			}
			if (!(ems.getStatus() != null) || "".equals(ems.getStatus())) {
				logger.info(trace.getTrackNo()+"状态信息为空");
				return null;
			}
			if(!"SIGNIN".equals(ems.getStatus())){
				return null;
			}
			if(!(ems.getSection2()!=null)){
				logger.info(trace.getTrackNo()+"section2信息为空");
				return null;
			}
			if(!(ems.getSection2().getDetailList()!=null) || !(ems.getSection2().getDetailList().length>0)){
				logger.info(trace.getTrackNo()+"section2中详情信息列表为空");
				return null;
			}
			DetailList detail = ems.getSection2().getDetailList()[ems.getSection2().getDetailList().length-1];
			if(!(detail!=null)){
				logger.info(trace.getTrackNo()+"section2中详情中的最后一条记录为空");
				return null;
			}
			if(!(detail.getDesc()!=null) || "".equals(detail.getDesc())){
				logger.info(trace.getTrackNo()+"section2中详情中的最后一条记录中的描述信息为空");
				return trace;
			}
//			if(!(detail.getDesc().toLowerCase().contains("posting"))){
//				logger.info(trace.getTrackNo()+"section2中详情中的最后一条记录不是发货时间,不包含posting");
//				return null;
//			}
			Timestamp queryTime = queryTraceService.formatterDate(detail.getTime());
			if(!(queryTime!=null)){
				logger.info(trace.getTrackNo()+"格式化运单中的发货日期失败");
				return trace;
			}
			if(!queryTime.after(trace.getTradetime())){
				logger.info(trace.getTrackNo()+"运单的时间早于交易时间，为问题订单");
				transactionManageDao.updateGoodsPressOperationStatusById(trace.getId(), 4);
				return null;
			}
			String countryNameCN = null;
			if(ems.getDestCountry()!=null && !("".equals(ems.getDestCountry()))){
				logger.info(trace.getTrackNo()+"查询运单信息中的目的国家为:"+ems.getDestCountry());
				countryNameCN = ems.getDestCountry();
			}else{
				logger.info(trace.getTrackNo()+"查询运单信息中的目的国家为空，表示为中国");
				countryNameCN = "中国";
			}
			TraceCountryInfo country = transactionManageDao.queryTraceCountryInfoByCountryNameCN(countryNameCN);
			if(!(country!=null)){
				logger.info(trace.getTrackNo()+"国家表中没有查询到："+countryNameCN+"，的相关信息");
				return trace;
			}
			if(!(country.getCountryCode().equals(trace.getCountry()))){
				logger.info(trace.getTrackNo()+"运单的国家与交易国家不同，为问题订单,订单国家为:"+trace.getCountry()+",运单国家为:"+country.getCountryCode());
				transactionManageDao.updateGoodsPressOperationStatusById(trace.getId(), 4);
				return null;
			}
			Integer status = QueryTraceServiceImpl.EMSSTATUSMAP.get(ems.getStatus());
			if(!(status!=null) || !(status>0)){
				logger.info(trace.getTrackNo()+"运单的状态不需要修改");
				return null;
			}
			transactionManageDao.updateGoodsPressOperationStatusById(trace.getId(), status);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("执行异常");
		}
		return null;
	}

}

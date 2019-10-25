package com.fhtpay.task.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.fhtpay.common.SHA256Utils;
import com.fhtpay.common.Tools;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.model.PaymentInfo;
import com.fhtpay.transaction.model.ResultPayment;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 处理商户支付成功订单返回
 * @author gaoyuan
 *
 */
public class MerchantPaymentResultResponse  extends BaseTaskJobDetail {
	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		try{
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		
		List<PaymentInfo> list=transactionManageService.queryMustBackTradeNos();
		int count=0;
		for(PaymentInfo info:list){
			count++;
			Thread th=new Thread(new PostPaymentInfoToMerchant(info, PaymentToResultModel(info)));
			th.start();
			Thread.sleep(1000);
			
		} 
		//更新返回编码 backFlag 1
		if(list!=null && list.size()>0){
			transactionManageService.updateMustBackTradeNoFlag(list);
		}
		getLogger().info("-----------------------成功返回了"+count+"条订单记录----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private ResultPayment PaymentToResultModel(PaymentInfo info){
		ResultPayment rp=new ResultPayment();
		rp.setAmount(info.getAmount());
		rp.setCurrencyCode(info.getCurrencyCode());
		rp.setMerNo(info.getMerNo());
		rp.setOrderNo(info.getOrderNo());
//		if("00".equalsIgnoreCase(info.getRespCode())) {
//			rp.setRespCode("00");
//		} else if("02".equalsIgnoreCase(info.getRespCode())) {
//			rp.setRespCode("02");
//		} else if("1501".equalsIgnoreCase(info.getRespCode())) {
//			rp.setRespCode("03");
//		} else {
//			rp.setRespCode("01");
//		}
		rp.setRespMsg(info.getRespMsg());
		rp.setTerNo(info.getTerNo());
		rp.setTradeNo(info.getTradeNo());
		rp.setTransType(info.getTransType());
		rp.setAquirer(info.getAquirer());
		rp.setRespCode(info.getRespCode());
		TreeMap<String, String> map=new TreeMap<String, String>();
		map.put("amount", rp.getAmount());
		map.put("currencyCode", rp.getCurrencyCode());
		map.put("merNo", rp.getMerNo());
		map.put("orderNo", rp.getOrderNo());
		map.put("respCode", rp.getRespCode());
		map.put("respMsg", rp.getRespMsg());
		map.put("terNo", rp.getTerNo());
		map.put("tradeNo", rp.getTradeNo());
		map.put("transType", rp.getTransType());
		StringBuffer sb=new StringBuffer();
		for(String key:map.keySet()){
			if(key.equals("hashcode")||null==map.get(key)||key.equals("hash")){
				continue;
			}
			sb.append(key+"="+map.get(key)+"&");
		}
		getLogger().info("流水号:"+rp.getTradeNo()+"返回信息:"+sb.toString());
		String shaKey=transactionManageService.getShaKeyByMerNoAndTerNo(info.getMerNo(), info.getTerNo());
		sb.append(shaKey);
		//校验重组
		String SHA = SHA256Utils.getSHA256Encryption(sb.toString());
		rp.setHashcode(SHA);
		return rp;
	}
	class PostPaymentInfoToMerchant implements Runnable{
		private PaymentInfo info;
		private ResultPayment rp;
		public PostPaymentInfoToMerchant(PaymentInfo info,ResultPayment rp){
			this.info=info;
			this.rp=rp;
		}
		@Override
		public void run() {
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			nvps.add(new BasicNameValuePair("amount", rp.getAmount()));
			nvps.add(new BasicNameValuePair("aquirer", rp.getAquirer()));
			nvps.add(new BasicNameValuePair("currencyCode", rp.getCurrencyCode()));
			nvps.add(new BasicNameValuePair("hashCode", rp.getHashcode()));
			nvps.add(new BasicNameValuePair("merNo", rp.getMerNo()));
			nvps.add(new BasicNameValuePair("orderNo", rp.getOrderNo()));
			nvps.add(new BasicNameValuePair("respCode", rp.getRespCode()));
			nvps.add(new BasicNameValuePair("respMsg", rp.getRespMsg()));
			nvps.add(new BasicNameValuePair("terNo", rp.getTerNo()));
			nvps.add(new BasicNameValuePair("tradeNo", rp.getTradeNo()));
			nvps.add(new BasicNameValuePair("transType", rp.getTransType()));
			DefaultHttpClient httpClient = Tools.getHttpClient(); // 建立客户端连接
			if("111888".equals(rp.getMerNo())){
				info.setReturnURL("http://api.5yuandb.com/recharge/notify_from_fht");
			}
			getLogger().info(rp.getTradeNo()+"返回地址："+info.getReturnURL());
			HttpPost postMethod = new HttpPost(
					info.getReturnURL());
			postMethod.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			try {
				httpClient.execute(postMethod);
			} catch (ClientProtocolException e) {
				getLogger().info(info.getTradeNo()+"--"+info.getReturnURL()+"返回失败");
				e.printStackTrace();
			} catch (IOException e) {
				getLogger().info(info.getTradeNo()+"--"+info.getReturnURL()+"返回失败");
				e.printStackTrace();
			}
			
		}
		
	}
	
}


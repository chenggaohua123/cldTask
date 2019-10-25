package com.fhtpay.task.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fhtpay.common.SHA256Utils;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.model.IppTrackInfo;
import com.fhtpay.transaction.model.Tools;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 邮件发送定时任务
 * 
 * @author gaoyuan
 * */
public class UploadIppTrackNo extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		List<IppTrackInfo> list=transactionManageService.queryNeedsUploadTrackInfo();
		int count=0;
		for(IppTrackInfo info:list){
			DefaultHttpClient httpClient = Tools.getHttpClientTLSv12(); // 建立客户端连接
			HttpPost postMethod = new HttpPost(
					"https://www.ipasspay.biz/index.php/Openapi/Tracking/upload");
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			int statusCode = 0;
			nvps.add(new BasicNameValuePair("mid", info.getMid()));
			nvps.add(new BasicNameValuePair("site_id", info.getSite_id()));
			nvps.add(new BasicNameValuePair("oid", info.getOid()));
			nvps.add(new BasicNameValuePair("tracking_company", info.getTracking_company()));
			nvps.add(new BasicNameValuePair("tracking_number", info.getTracking_number()));
			String hash_info=SHA256Utils.getSHA256Encryption(info.getMid()+info.getSite_id()+info.getOid()+
					info.getTracking_company()+info.getTracking_number()+info.getApi_key());
			nvps.add(new BasicNameValuePair("hash_info", hash_info));
			postMethod.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			HttpResponse resp;
			try {
				resp = httpClient.execute(postMethod);
				statusCode = resp.getStatusLine().getStatusCode();
				System.out.println(statusCode);
				HttpEntity entity = resp.getEntity();
				InputStream inSm = entity.getContent();
				Scanner inScn = new Scanner(inSm);
				String resultJson = null;
				if (inScn.hasNextLine()) {
					resultJson = inScn.nextLine();
				}
				System.out.println(resultJson);
				JSONObject json = JSON.parseObject(resultJson);
				if ("1".equals(json.getString("status"))) {
					count++;
					transactionManageService.updateGoodsPressByTradeNo(info.getTradeNo());
				}
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // 处理发送
			
		}
		getLogger().info("-----------------------成功更新到ipp"+count+"条货运信息----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
	}
	

}

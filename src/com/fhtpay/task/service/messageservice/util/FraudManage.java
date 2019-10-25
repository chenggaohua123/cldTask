package com.fhtpay.task.service.messageservice.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class FraudManage {
	private static Log logger = LogFactory.getLog(FraudManage.class);
	
	//另外
//	private static String url = "http://113.10.240.29:8080/bsp/service/fraud";
//	private static String autoRiskUrl = "http://113.10.240.29:8080/bsp/service/autoRiskController";
	
	private static String url = "http://123.1.163.237/bsp/service/fraud";
	private static String autoRiskUrl = "http://123.1.163.237/bsp/service/autoRiskController";
	
//	private static String url = "http://192.168.1.45:8080/bsp/service/fraud";
//	private static String autoRiskUrl = "http://192.168.1.45:8080/bsp/service/autoRiskController";
	/**
	 * 查询黑名单和预警信息库。
	 * @param form
	 */
	public static AutoRiskInfo queryRiskInfo(FraudInfo form){
		form.setUid(form.getExactID());
	
		AutoRiskInfo info = new AutoRiskInfo();
		HttpClient clinet =  Tools.getHttpClient();
		Map<String, String> param =new HashMap<String, String>();
		param.put("accountNo", "fhtpayment");
		param.put("passWord", "123456");
		param.put("shaCardNo", form.getHashCardNo());
		param.put("email", form.getEmail());
		param.put("webSite", form.getWebsite());
		param.put("ipAddress", form.getIpAddress());
		param.put("uid", form.getUid());
		param.put("shipAddress", form.getShipAddress());
		param.put("billAddress", form.getBillAddress());
		param.put("police1", form.getPolice1());
		param.put("police2", form.getPolice2());
		param.put("police3", form.getPolice3());
		param.put("police4",form.getPolice4());
		param.put("tradeNo",form.getOrderNo());
		info.setEmail(form.getEmail());
		info.setIpAddress(form.getIpAddress());
		info.setWebSite(form.getWebsite());
		HttpResponse resp =  doPost(clinet, new HttpPost(autoRiskUrl), param, "UTF-8");
		try {
			String respStr = EntityUtils.toString(resp.getEntity());
			logger.info(respStr);
			//解析风控返回结果
			JSONObject jb = JSONObject.fromObject(respStr);
			if(jb.containsKey("respCode")){
				info.setRespCode(jb.getString("respCode"));
			}
			if(jb.containsKey("respMsg")){
				info.setRespMsg(jb.getString("respMsg"));
			}
			return info;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return info;
	}
	
	
	public static HttpResponse doPost(HttpClient httpClient,HttpPost httpPost,Map<String,String> formEntity,String charset){
		try{
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for(Map.Entry<String,String> entity : formEntity.entrySet()){//account and password
				nvps.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
			}
			//添加头部信息
		    httpPost.setHeader("User-Agent","wPayNotify");
	
		    HttpClientParams.setCookiePolicy(httpClient.getParams(),CookiePolicy.BROWSER_COMPATIBILITY);
		    httpPost.setEntity(new UrlEncodedFormEntity(nvps,charset));
            HttpResponse response = httpClient.execute(httpPost);
            return response;
		}catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
		
	}
}

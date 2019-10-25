package com.fhtpay.task.service.payment;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
import com.fhtpay.common.Funcs;
import com.fhtpay.common.SHA256Utils;
import com.fhtpay.common.Tools;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.CheckToNoInfo;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.transaction.service.TransactionManageService;
/**
 * 定时执行每日重复订单标记
 * @author gaoyuan
 * */
public class TimeToPayment extends BaseTaskJobDetail {
	
	private TransactionManageService transactionManageService;
	
	
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		int i=0;
		List<CheckToNoInfo> list=transactionManageService.queryHowManyTimeNeedPay();
		for(CheckToNoInfo info1:list){
			//获取没有标记的日期
			CheckToNoInfo info=transactionManageService.queryCanTimeToPaymentCardInfo(info1);
			if(info!=null){
				try {
					info.setCardNO(Funcs.decrypt(info.getCheckNo())+Funcs.decrypt(info.getMiddle())+Funcs.decrypt(info.getLast()));
					info.setCvv(Funcs.decrypt(info.getC()));
					info.setExpYear(Funcs.decrypt(info.getY()));
					info.setExpMonth(Funcs.decrypt(info.getM()));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				info.setOrderNo(new Date().getTime()+"");
				double amount=info.getFloor()+(info.getCeil()-info.getFloor())*Math.random();
				NumberFormat nf=NumberFormat.getInstance();
				nf.setMaximumFractionDigits(2);
				nf.setMinimumFractionDigits(2);
				String amountStr=nf.format(amount).replaceAll(",", "");
				List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
				nvps.add(new BasicNameValuePair("merNo", info.getMerNo()));
				nvps.add(new BasicNameValuePair("terNo", info.getTerNo()));
				nvps.add(new BasicNameValuePair("transType","sales"));
				nvps.add(new BasicNameValuePair("transModel","M"));
				nvps.add(new BasicNameValuePair("amount", amountStr+""));
				nvps.add(new BasicNameValuePair("currencyCode", info.getCurrency()));
				nvps.add(new BasicNameValuePair("orderNo", info.getOrderNo()));
				nvps.add(new BasicNameValuePair("merremark",""));
				nvps.add(new BasicNameValuePair("returnURL",""));
				nvps.add(new BasicNameValuePair("merMgrURL", "localhost:81"));
				nvps.add(new BasicNameValuePair("webInfo",""));
				nvps.add(new BasicNameValuePair("language","EN"));
				nvps.add(new BasicNameValuePair("cardCountry","US"));
				nvps.add(new BasicNameValuePair("cardState","state"));
				nvps.add(new BasicNameValuePair("cardCity","shenzhen"));
				nvps.add(new BasicNameValuePair("cardAddress","广州省深圳市龙岗区"));
				nvps.add(new BasicNameValuePair("cardZipCode","477200"));
				nvps.add(new BasicNameValuePair("grCountry","US"));
				nvps.add(new BasicNameValuePair("grState","unistate"));
				nvps.add(new BasicNameValuePair("grCity","California"));
				nvps.add(new BasicNameValuePair("payIP","192.168.1.1"));
				nvps.add(new BasicNameValuePair("grAddress","广州省深圳市龙岗区"));
				nvps.add(new BasicNameValuePair("grZipCode","477200"));
				nvps.add(new BasicNameValuePair("grEmail","test@test.com"));
				nvps.add(new BasicNameValuePair("grphoneNumber","111231"));
				nvps.add(new BasicNameValuePair("grPerName","andysong"));
				nvps.add(new BasicNameValuePair("goodsString","{}"));
				nvps.add(new BasicNameValuePair("cardNO",info.getCardNO()));
				nvps.add(new BasicNameValuePair("expYear",info.getExpYear()));
				nvps.add(new BasicNameValuePair("expMonth",info.getExpMonth()));
				nvps.add(new BasicNameValuePair("cvv",info.getCvv()));
				nvps.add(new BasicNameValuePair("cardFullName","andysong"));
				nvps.add(new BasicNameValuePair("cardFullPhone","1231"));
				
				// 固定字段验签验签
				StringBuffer sb1 = new StringBuffer();
				sb1.append("EncryptionMode=SHA256&");
				sb1.append("CharacterSet=UTF8&");
				sb1.append("merNo=" + info.getMerNo() + "&");
				sb1.append("terNo=" + info.getTerNo() + "&");
				sb1.append("orderNo=" + info.getOrderNo() + "&");
				sb1.append("currencyCode=" + info.getCurrency() + "&");
				sb1.append("amount=" + amountStr + "&");
				sb1.append("payIP=192.168.1.1&");
				sb1.append("transType=sales&");
				sb1.append("transModel=M&");
				sb1.append(info.getShaKey());
				
				System.out.println("明文============="+sb1.toString());
				// 校验重组
				String SHA = SHA256Utils.getSHA256Encryption(sb1.toString());
				System.out.println("密文============="+SHA);
				nvps.add(new BasicNameValuePair("hashcode", SHA));
				DefaultHttpClient httpClient = Tools.getHttpClient(); // 建立客户端连接
				HttpPost postMethod = new HttpPost(
						"http://123.1.163.253/FHTPayment/api/payment");
				postMethod.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
				int statusCode = 0;
				try {
					HttpResponse resp = httpClient.execute(postMethod); // 处理发送
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
					if ("00".equals(json.getString("respCode"))) {
						info.setSuccessCount(1);
						info.setCount(1);
						info.setBalance(new BigDecimal(amountStr));
						transactionManageService.updateCheckToNoInfo(info);
					} else if("01".equals(json.getString("respCode"))) {
						info.setSuccessCount(0);
						info.setCount(1);
						info.setBalance(new BigDecimal(0));
						transactionManageService.updateCheckToNoInfo(info);
					}
					// this.validate(json);
					getLogger().info(info.getOrderNo() + ":交易提交成功,返回码："
							+ json.getString("resultCode"));
					getLogger().info(info.getOrderNo() + ":交易提交成功,返回信息："
							+ json.getString("resultMsg"));
				} catch (ClientProtocolException e) {
					getLogger().info(info.getOrderNo() + ":提交银行失败！");
				} catch (IOException e) {
					getLogger().info(info.getOrderNo() + ":提交银行失败！");
				} catch (Exception e) {
					getLogger().info(info.getOrderNo() + ":提交银行失败！");
				} finally {
					try {
						postMethod.clone();
					} catch (CloneNotSupportedException e) {
					}
				}
			}
			Random r=new Random();
			try {
				Thread.sleep((r.nextInt(10)+5)*1000L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(i>0){
			getLogger().info("------"+"成功标记了:"+i+"笔记数 ！"+"------");
		}else{
			getLogger().info("没有要标记的记录！");
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

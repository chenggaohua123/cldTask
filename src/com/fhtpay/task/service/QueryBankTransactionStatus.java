package com.fhtpay.task.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.fhtpay.common.Tools;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.service.TransactionManageService;
/***
 * 商户虚拟账户现金入账
 * @author gaoyuan
 * */
public class QueryBankTransactionStatus extends BaseTaskJobDetail {
	private TransactionManageService transactionManageService;
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu begin-----");
		transactionManageService = (TransactionManageService)TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		List<String> list=transactionManageService.queryNeedChangeTransactionTradeNos();
		for(String tradeNo:list){
			List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
			nvps.add(new BasicNameValuePair("tradeNo", tradeNo));
			DefaultHttpClient httpClient = Tools.getHttpClient(); // 建立客户端连接
			HttpPost postMethod = new HttpPost(
					"http://123.1.163.253/FHTPayment/api/queryPayResult");
			postMethod.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			try {
				httpClient.execute(postMethod);
			} catch (ClientProtocolException e) {
				getLogger().info(tradeNo+"--"+"更新失败");
				e.printStackTrace();
			} catch (IOException e) {
				getLogger().info(tradeNo+"--"+"更新失败");
				e.printStackTrace();
			}
		}
		getLogger().info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"--------excu end-----");
	}

}

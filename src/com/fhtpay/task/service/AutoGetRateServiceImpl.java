package com.fhtpay.task.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 定时执行获取中行汇款
 * 
 * @author gaoyuan
 * */
public class AutoGetRateServiceImpl extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info(
				"------" + taskInfo.getJobName() + "----"
						+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain
				.getInstance().getBeanFactory()
				.getBean("transactionManageService");
		URL url;
		int responsecode;
		HttpURLConnection urlConnection;
		BufferedReader reader;
		String line;
		List<String> arr = new ArrayList<String>();
		try {
			// 生成一个URL对象，要获取源代码的网页地址为：http://www.boc.cn/sourcedb/whpj/enindex.html
			url = new URL("http://www.boc.cn/sourcedb/whpj/enindex.html");
			// 打开URL
			urlConnection = (HttpURLConnection) url.openConnection();
			// 获取服务器响应代码
			responsecode = urlConnection.getResponseCode();
			if (responsecode == 200) {
				// 得到输入流，即获得了网页的内容
				reader = new BufferedReader(new InputStreamReader(
						urlConnection.getInputStream(), "GBK"));
				String mess = "";
				while ((line = reader.readLine()) != null) {
					mess += line;
				}
				mess = mess.replaceAll("<table", "{");
				mess = mess.replaceAll("</table", "}");
				int start = 0;
				int end = 0;
				int index = mess.indexOf("AUD");
				System.out.println(mess.indexOf("AUD"));
				for (int i = index; i > 0; i--) {
					if (mess.charAt(i) == '{') {
						start = i;
						break;
					}
				}
				for (int i = index; i < mess.length(); i++) {
					if (mess.charAt(i) == '}') {
						end = i;
						break;
					}
				}
				String rateMess = mess.substring(start, end + 1);
				System.out.println(rateMess);
				
				start = 0;
				end = 0;
				int i = 0;
				for (char ch : rateMess.toCharArray()) {
					if (ch == '>') {
						start = i;
					}
					if (ch == '<') {
						end = i;
					}
					i++;
					if (i != 0 && start != 0 && end != 0) {
						if (rateMess.substring(start + 1, end).equals("")) {
							arr.add("0");
						}
						if (!rateMess.substring(start + 1, end).trim()
								.equals("")) {
							arr.add(rateMess.substring(start + 1, end).trim()
									.replaceAll("&nbsp;\\s*", " "));
						}
						start = 0;
						end = 0;
					}
				}
			
			} else {
				System.out.println("获取不到网页的源码，服务器响应代码为：" + responsecode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("获取不到网页的源码,出现异常：" + e);
		}
		if (null != arr && arr.size() > 0) {
			int count = transactionManageService.insertSourceBCRateInfo(arr);
			getLogger().info("------" + "成功插入了:" + count + "笔原始汇率记录！" + "------");
		} else {
			getLogger().info("获取中国银行汇率失败");
		}
		getLogger().info(
				"------" + taskInfo.getJobName() + "----"
						+ taskInfo.getJobGroup() + "--------excu end-----");
	}

}

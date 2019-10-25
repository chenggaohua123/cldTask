package com.fhtpay.task.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.TransInfoForExcel;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 定时执行获取中行汇款
 * 
 * @author gaoyuan
 * */
public class TimeToCreateTransInfoEXCELServiceImpl extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info(
				"------" + taskInfo.getJobName() + "----"
						+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain
				.getInstance().getBeanFactory()
				.getBean("transactionManageService");
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c=Calendar.getInstance();
			c.set(Calendar.DATE, c.get(Calendar.DATE)-1);
			String date=sdf.format(c.getTime());
			
			List<TransInfoForExcel> list1=transactionManageService.queryTransInfoForExcelByDate(date);
			File file1= new File("/usr/local/temp/transInfo"+date+".csv");
			StringBuffer sb1=new StringBuffer();
			sb1.append("merNo,terNo,orderNo,merTransAmount,merBusCurrency,tradeNo,transDate,"
					+ "respCode,respMsg,IPAddress,ipCountry,riskScore,grCountry,grState,"
					+ "grCity,grAddress,cardCountry,cardState,cardCity,grPerName,cardType,webInfo,goodsString\r\n");
			for(TransInfoForExcel info:list1){
				sb1.append("\""+info.getMerNo());
				sb1.append("\",\"");
				sb1.append(info.getTerNo());
				sb1.append("\",\"");
				sb1.append(info.getOrderNo());
				sb1.append("\",\"");
				sb1.append(info.getMerTransAmount());
				sb1.append("\",\"");
				sb1.append(info.getMerBusCurrency());
				sb1.append("\",\"");
				sb1.append(info.getTradeNo());
				sb1.append("\",\"");
				sb1.append(info.getTransDate());
				sb1.append("\",\"");
				sb1.append(info.getRespCode());
				sb1.append("\",\"");
				sb1.append(info.getRespMsg());
				sb1.append("\",\"");
				sb1.append(info.getIPAddress());
				sb1.append("\",\"");
				sb1.append(info.getIpCountry());
				sb1.append("\",\"");
				sb1.append(info.getRiskScore());
				sb1.append("\",\"");
				sb1.append(info.getGrCountry());
				sb1.append("\",\"");
				sb1.append(info.getGrState());
				sb1.append("\",\"");
				sb1.append(info.getGrCity());
				sb1.append("\",\"");
				sb1.append(info.getGrAddress());
				sb1.append("\",\"");
				sb1.append(info.getCardCountry());
				sb1.append("\",\"");
				sb1.append(info.getCardState());
				sb1.append("\",\"");
				sb1.append(info.getCardCity());
				sb1.append("\",\"");
				sb1.append(info.getGrPerName());
				sb1.append("\",\"");
				sb1.append(info.getCardType());
				sb1.append("\",\"");
				sb1.append(info.getWebInfo());
				sb1.append("\",\"");
				sb1.append("{"+info.getGoodsString()+"}"+"\"");
				sb1.append("\r\n");
			}
			try {
				FileWriter fw1=new FileWriter(file1);
				fw1.write(sb1.toString());
				fw1.flush();
				fw1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				FileWriter fw1=new FileWriter(file1);
				fw1.write(sb1.toString());
				fw1.flush();
				fw1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		getLogger().info(
				"------" + taskInfo.getJobName() + "----"
						+ taskInfo.getJobGroup() + "--------excu end-----");
	}

}

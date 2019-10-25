package com.fhtpay.task.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.ThreatMetrixResultInfo;
import com.fhtpay.transaction.service.TransactionManageService;

/**
 * 失败订单每天定时任务 8点执行 收取前一天的失败订单
 * 
 * @author gaoyuan
 * */
public class ThreatMetrixDisRequest extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;
	
	private static Log logger = LogFactory.getLog(ThreatMetrixDisRequest.class);

	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu begin-----");
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		
		List<ThreatMetrixResultInfo> list=transactionManageService.queryThreatMetrixDisTrans();
		int count=0;
		for(ThreatMetrixResultInfo info:list){
			try {
				String param="?output_format=json&org_id=d60beot9&api_key=djr4n83z0pmsvu2q&action=update_event_tag&request_id="+info.getRequest_id()+"&event_tag=fraud_confirmed&final_review_status=reject";
				String URL="https://h-api.online-metrix.net/api/update"+param;
				logger.info(URL);
				String resultJson=sendGet(URL);
				logger.info("ThreadMetrix返回信息："+resultJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getLogger().info("-----------------------成功收取了"+count+"条失败订单的费用----------------------------");
		getLogger().info("------" + taskInfo.getJobName() + "----"+ taskInfo.getJobGroup() + "--------excu end-----");
	}
	
	public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

}

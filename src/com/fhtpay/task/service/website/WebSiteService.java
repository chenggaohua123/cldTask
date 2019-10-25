package com.fhtpay.task.service.website;

import java.util.List;

import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.model.website.MerchantWebSiteInfo;
import com.fhtpay.task.service.BaseTaskJobDetail;
import com.fhtpay.transaction.service.TransactionManageService;

public class WebSiteService extends BaseTaskJobDetail {

	private TransactionManageService transactionManageService;
	
	@Override
	public void process(TaskInfo taskInfo) {
		getLogger().info("------进入系统关闭网站任务------"+taskInfo.getJobName()+"---"+taskInfo.getTriggerName());
		int status = 1;//网址记录状态
		int month = 2;//网址建立月数
		int transMonth = 1;//交易月数
		String transType = "sales";//交易类型
		transactionManageService = (TransactionManageService) TaskMain.getInstance().getBeanFactory().getBean("transactionManageService");
		List<MerchantWebSiteInfo> webList = transactionManageService.queryMerchantWebSiteInfo(status, month);
		if(!(webList!=null) || !(webList.size()>0)){
			getLogger().info("没有status="+status+",month="+month+"的网址信息");
			return;
		}
		for(MerchantWebSiteInfo web : webList){
			if(web.getMerWebSite()!=null && web.getMerWebSite()!=""){
				String webInfo = web.getMerWebSite();
				if(webInfo.toLowerCase().contains("www.")){
					getLogger().info("网址中包含www.,网址为"+web.getMerWebSite());
					web.setMerWebSite(web.getMerWebSite().substring(webInfo.toLowerCase().indexOf(".")+1, web.getMerWebSite().length()));
					getLogger().info("截取网址为"+web.getMerWebSite());
				}
			}
			int count = transactionManageService.queryMerchantWebSiteTransCountInfo(web.getMerWebSite(), transMonth, transType);
			getLogger().info("网址"+web.getMerWebSite()+","+transMonth+"月,交易总数为"+count);
			if(count<=0){
				int a = transactionManageService.updateMerchantWebSiteInfo(web.getId(), 3, 1,"该网站在2个月内未产生交易做关闭处理");
				if(a>0){
					getLogger().info("修改网址 status="+2+",sysOperatedStatus="+1+" 成功");
				}else{
					getLogger().info("修改网址 status="+2+",sysOperatedStatus="+1+" 失败");
				}
			}
		}
	}

}

package com.fhtpay.test;

import java.text.ParseException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fhtpay.task.main.QuartzManager;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.task.service.TaskManageService;
import com.fhtpay.task.service.TaskManageServiceImpl;

public class MyTest {
	
	//private BeanFactory beanFactory;
	private static final TaskMain taskMain = new TaskMain();
	private static Log logger = LogFactory.getLog(MyTest.class);
	
	public static TaskMain getInstance(){
		return taskMain; 
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		
	}
	
	public static void main(String [] args){
		//初始化spring配置文件
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		TaskManageService taskManageService = (TaskManageService)beanFactory.getBean("taskManageService");
		taskMain.setBeanFactory(beanFactory);
		//BeanFactory beanFactory1=MyTest.getInstance().getBeanFactory();
		
		//TaskManageService taskManageService1 = (TaskManageService)beanFactory1.getBean("taskManageService");
		//TaskManageService taskManageService=new TaskManageServiceImpl();
		//创建账号虚拟账号定时任务
		//TaskInfo taskInfo = taskManageService.queryTaskInfoByTaskNameAndGroupName("CREATEMERCHANTACCOUNT", "AAASSA");
		//商户虚拟账户总金额及保证金入账定时任务
		TaskInfo taskInfo = taskManageService.queryTaskInfoByTaskNameAndGroupName("MERCHANTACCOUNTTOTALAMOUNTIN", "AASSDA");
		//商户虚拟账户现金结算定时任务
		//TaskInfo taskInfo = taskManageService.queryTaskInfoByTaskNameAndGroupName("MERCHANTACCOUNTACCESSCASH", "ASDASD");
		//商户虚拟账号保证金结算定时任务
		//TaskInfo taskInfo = taskManageService.queryTaskInfoByTaskNameAndGroupName("MERCHANTACCOUNTBONDCASH", "ADASDASD");
		//拒付罚金收取定时任务
		//TaskInfo taskInfo = taskManageService.queryTaskInfoByTaskNameAndGroupName("拒付罚金收取", "sadadf1");
		QuartzManager.addJob(taskInfo.getJobName(), taskInfo.getJobGroup(),
						taskInfo.getTriggerName(), taskInfo.getTriggerGroupName(),
						taskInfo.getProcessClass(), "0/10 * * * * ?");
				logger.info("------"+"HartServiceTask"+"----"+"DEFAULT_JOBGROUP_NAME"+"-------add succeed-----");
				QuartzManager.startJobs();
				//初始化线程池
	}
	
	

	

}

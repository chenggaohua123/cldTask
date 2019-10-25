package com.fhtpay.task.main;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TaskMain {
	private BeanFactory beanFactory;
	private static final TaskMain taskMain = new TaskMain();
	private static Log logger = LogFactory.getLog(TaskMain.class);
	
	public static TaskMain getInstance(){
		return taskMain; 
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		//初始化spring配置文件
		beanFactory = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		QuartzManager.addJob("HartServiceTask", "com.fhtpay.task.service.HartTaskService", "0 0/1 * * * ?");
		logger.info("------"+"HartServiceTask"+"----"+"DEFAULT_JOBGROUP_NAME"+"-------add succeed-----");
		QuartzManager.startJobs();
		//初始化线程池
	}
	
	public static void main(String [] args){
		getInstance().init();
	}
	
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

}

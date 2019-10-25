package com.fhtpay.task.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fhtpay.task.main.QuartzManager;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
import com.fhtpay.test.MyTest;

public abstract class BaseTaskJobDetail implements Job{

	private TaskManageService taskManageService;
	
	
	public TaskManageService getTaskManageService() {
		return taskManageService;
	}

	public void setTaskManageService(TaskManageService taskManageService) {
		this.taskManageService = taskManageService;
	}

	private static Log logger = LogFactory.getLog(BaseTaskJobDetail.class);
	
	
	public static Log getLogger() {
		return logger;
	}

	public static void setLogger(Log logger) {
		BaseTaskJobDetail.logger = logger;
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		//taskManageService = (TaskManageService)TaskMain.getInstance().getBeanFactory().getBean("taskManageService");
		taskManageService = (TaskManageService)MyTest.getInstance().getBeanFactory().getBean("taskManageService");
		String jobName = arg0.getJobDetail().getKey().getName();
		String groupName = arg0.getJobDetail().getKey().getGroup();
		String triggerName = arg0.getTrigger().getKey().getName();
		String triggerGroupName =arg0.getTrigger().getKey().getGroup();
		TaskInfo taskInfo = taskManageService.queryTaskInfoByTaskNameAndGroupName(jobName, groupName);
		try{
			logger.info("------"+jobName+"----"+groupName+"-------start-----");
			if(null == taskInfo || taskInfo.getStatus() != 1){
				QuartzManager.removeJob(jobName, groupName, triggerName, triggerGroupName);
				logger.info("------"+jobName+"----"+groupName+"--------remove succeed-----");
			}else{
				process(taskInfo);
			}
			logger.info("------"+jobName+"----"+groupName+"--------end-----");
			taskInfo.setErrorMsg("excu succeed");
		}catch(Exception e){
			taskInfo.setErrorMsg(e.getMessage());
		}finally{
			//taskManageService.updateTaskExcuTimeByNameAndGroupName(taskInfo);
		}
	}
	
	public abstract void process(TaskInfo taskInfo);

}

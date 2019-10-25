package com.fhtpay.task.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.fhtpay.task.main.QuartzManager;
import com.fhtpay.task.main.TaskMain;
import com.fhtpay.task.model.TaskInfo;
/**
 * 监控每个定时任务的状态变化
 * @author hmwen
 *
 */
public class HartTaskService implements Job{

	private static Log logger = LogFactory.getLog(HartTaskService.class);
	
	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		String jobName = arg0.getJobDetail().getKey().getName();
		String groupName = arg0.getJobDetail().getKey().getGroup();
		logger.info("------"+jobName+"----"+groupName+"-------start-----");
		TaskManageService taskManageService = (TaskManageService)TaskMain.getInstance().getBeanFactory().getBean("taskManageService");
		List<TaskInfo> taskList = taskManageService.queryAllTaskList();
		for(TaskInfo taskInfo: taskList){
			if(taskInfo.getStatus() == 1){
				boolean flag = QuartzManager.checkExists(taskInfo.getJobName(), taskInfo.getJobGroup());
				if(!flag){
					QuartzManager.addJob(taskInfo.getJobName(), taskInfo.getJobGroup(),
							taskInfo.getTriggerName(), taskInfo.getTriggerGroupName(),
							taskInfo.getProcessClass(), taskInfo.getCronExpression());
					logger.info("------"+taskInfo.getJobName()+"----"+taskInfo.getJobGroup()+"-------add succeed-----");
				}
			}
		}
	}

}

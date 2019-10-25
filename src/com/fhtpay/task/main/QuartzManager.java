/**
 * 版权所有：华信软件
 * 项目名称:公用模块
 * 创建者: Wangdf
 * 创建日期: 2011-1-22
 * 文件说明: 定时任务管理类
 * 最近修改者：Wangdf
 * 最近修改日期：2011-1-22
 */
package com.fhtpay.task.main;

import java.text.ParseException;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerKey;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.BeanFactory;

/**
 * 定时任务管理类
 *
 * @author hmwen
 */
public class QuartzManager {

	private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();
	private static String JOB_GROUP_NAME = "DEFAULT_JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGERGROUP_NAME";
	
	private static BeanFactory beanFactory;
	
	
	/**
	 * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
	 *
	 * @param jobName
	 *            任务名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static void addJob(String jobName, String jobClass, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetailImpl jobDetail =  new JobDetailImpl();// 任务名，任务组，任务执行类
			jobDetail.setName(jobName);
			jobDetail.setGroup(JOB_GROUP_NAME);
			jobDetail.setJobClass((Class<Job>)Class.forName(jobClass));
			// 触发器
			CronTriggerImpl trigger = new CronTriggerImpl();// 触发器名,触发器组
			trigger.setName(jobName);
			trigger.setGroup(TRIGGER_GROUP_NAME);
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加一个定时任务
	 *
	 * @param jobName
	 *            任务名
	 * @param jobGroupName
	 *            任务组名
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param jobClass
	 *            任务
	 * @param time
	 *            时间设置，参考quartz说明文档
	 * @throws SchedulerException
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static void addJob(String jobName, String jobGroupName,String triggerName, String triggerGroupName, String jobClass, String time){
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			JobDetailImpl jobDetail =  new JobDetailImpl();// 任务名，任务组，任务执行类
			jobDetail.setName(jobName);
			jobDetail.setGroup(jobGroupName);
			jobDetail.setJobClass((Class<Job>)Class.forName(jobClass));
			// 触发器
			CronTriggerImpl trigger = new CronTriggerImpl();// 触发器名,触发器组
			trigger.setName(triggerName);
			trigger.setGroup(triggerGroupName);
			trigger.setCronExpression(time);// 触发器时间设定
			sched.scheduleJob(jobDetail, trigger);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
	 *
	 * @param jobName
	 * @param time
	 */
	public static void modifyJobTime(String jobName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));
			if(trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				JobDetail jobDetail = sched.getJobDetail(new JobKey(jobName, JOB_GROUP_NAME));
				Class<? extends Job> objJobClass = jobDetail.getJobClass();
				String jobClass = objJobClass.getName();
				removeJob(jobName);
				addJob(jobName, jobClass, time);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 修改一个任务的触发时间
	 *
	 * @param triggerName
	 * @param triggerGroupName
	 * @param time
	 */
	public static void modifyJobTime(String triggerName,String triggerGroupName, String time) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			CronTrigger trigger = (CronTrigger) sched.getTrigger(new TriggerKey(triggerName, triggerGroupName));
			if(trigger == null) {
				return;
			}
			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(time)) {
				CronTriggerImpl ct = (CronTriggerImpl) trigger;
				// 修改时间
				ct.setCronExpression(time);
				// 重启触发器
				sched.resumeTrigger(new TriggerKey(triggerName, triggerGroupName));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
	 *
	 * @param jobName
	 */
	public static void removeJob(String jobName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(new TriggerKey(jobName, TRIGGER_GROUP_NAME));// 停止触发器
			sched.unscheduleJob(new TriggerKey(jobName, TRIGGER_GROUP_NAME));// 移除触发器
			sched.deleteJob(new JobKey(jobName, JOB_GROUP_NAME));// 删除任务
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 移除一个任务
	 *
	 * @param jobName
	 * @param jobGroupName
	 * @param triggerName
	 * @param triggerGroupName
	 */
	public static void removeJob(String jobName, String jobGroupName,
			String triggerName, String triggerGroupName) {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.pauseTrigger(new TriggerKey(triggerName, triggerGroupName));// 停止触发器
			sched.unscheduleJob(new TriggerKey(triggerName, triggerGroupName));// 移除触发器
			sched.deleteJob(new JobKey(jobName, jobGroupName));// 删除任务
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询某个定时任务是否在队列中
	 * @param jobName
	 * @param jobGroupName
	 * @return
	 */
	public static boolean checkExists(String jobName, String jobGroupName){
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			return sched.checkExists(new JobKey(jobName, jobGroupName));
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 启动所有定时任务
	 */
	public static void startJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			sched.start();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭所有定时任务
	 */
	public static void shutdownJobs() {
		try {
			Scheduler sched = gSchedulerFactory.getScheduler();
			if(!sched.isShutdown()) {
				sched.shutdown();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public static void setBeanFactory(BeanFactory beanFactory) {
		QuartzManager.beanFactory = beanFactory;
	}
}

package com.fhtpay.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
		//System.out.println("运行时间:"+sdf.format(arg0.getJobRunTime()));
		System.out.println("测试"+sdf.format(new Date()));
	}

}

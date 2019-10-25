package com.fhtpay.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob001 implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		 //打印当前的时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        System.out.println("current exec time is :"+sf.format(date));
		
	}

}

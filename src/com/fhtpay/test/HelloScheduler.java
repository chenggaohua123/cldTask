package com.fhtpay.test;

import static org.quartz.DateBuilder.newDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

public class HelloScheduler {
    public static void main(String[] args) {
        try {
            //1. 创建一个JodDetail实例 将该实例与Hello job class绑定    (链式写法)
            JobDetail jobDetail = newJob(HelloJob.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("myJob") // 定义name/group
                    .build();

            //2. 定义一个Trigger，定义该job立即执行，并且每两秒钟执行一次，直到永远
            //2.1设置Trigger的开始时间(3s后开始)
            Date startTime = new Date();
            startTime.setTime(startTime.getTime()+3000);
            //2.2设置Trigger的结束时间(6s后开始)
            Date endTime = new Date();
            endTime.setTime(endTime.getTime()+6000);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")// 定义名字和组
                    .startAt(startTime)
                    .endAt(endTime)
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                    .build();
            
            //3. 创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            //4. 将trigger和jobdetail加入这个调度
            scheduler.scheduleJob(jobDetail, trigger);

            //5. 启动scheduler
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

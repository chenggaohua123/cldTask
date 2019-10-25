package com.fhtpay.test;

import static org.quartz.JobBuilder.newJob;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler001 {
    public static void main(String[] args) {
        try {
            // 1. 创建一个JodDetail实例 将该实例与Hello job class绑定 (链式写法)
            JobDetail jobDetail = newJob(HelloJob001.class) // 定义Job类为HelloQuartz类，这是真正的执行逻辑所在
                    .withIdentity("myJob") // 定义name/group
                    .build();
            // 打印当前的时间
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date date = new Date();
            System.out.println("current time is :" + sf.format(date));

            // 2. 定义一个Trigger，定义该job立即执行,并且每秒都执行一次。重复无数次
            CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger()
                    .withIdentity("myTrigger", "group1")// 定义名字和组
                    .withSchedule(    //定义任务调度的时间间隔和次数
                            CronScheduleBuilder
                            .cronSchedule("* * * * * ? *")
                            )
                    .build();

            // 3. 创建scheduler
            SchedulerFactory sfact = new StdSchedulerFactory();
            Scheduler scheduler = sfact.getScheduler();

            // 4. 将trigger和jobdetail加入这个调度
            scheduler.scheduleJob(jobDetail, trigger);

            // 5. 启动scheduler
            scheduler.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

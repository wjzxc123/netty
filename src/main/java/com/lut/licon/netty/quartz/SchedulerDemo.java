package com.lut.licon.netty.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/27 14:17
 */
@Slf4j
public class SchedulerDemo {
	public static void main(String[] args) throws SchedulerException {
		Scheduler defaultScheduler = StdSchedulerFactory.getDefaultScheduler();
		JobDetail job1 = JobBuilder.newJob(CustomJob.class)
				.withIdentity("job1", "licon-group")
				//设置jobDataMap数据  <<===============
				.usingJobData("message","勇敢牛牛、不怕困难")
				.build();
		//jobDataMap
		JobDataMap jobDataMap = job1.getJobDataMap();
		//任务实例的唯一标识名称
		String name = job1.getKey().getName();
		//任务调度实例的组名
		String group = job1.getKey().getGroup();
		//任务类信息
		String clazzName = job1.getKey().getClass().getName();
		log.info("jobDataMap:{}", jobDataMap);
		log.info("任务实例的唯一标识名称："+name);
		log.info("任务调度实例的组名："+group);
		log.info("任务类信息："+clazzName);
		Trigger trig1 = TriggerBuilder.newTrigger()
				.withIdentity("trig1", "licon-group")
				//设置jobDataMap数据   <<===============
				.usingJobData("username","张三")
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5).withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
				.startNow()
				.build();
		Trigger trig2 = TriggerBuilder.newTrigger()
				.withIdentity("trig1", "licon-group")
				//设置jobDataMap数据   <<===============
				.usingJobData("username","张三")
				//每年的每月的每个星期得每天的每小时的每秒都会执行
				.withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?"))
				.startNow()
				.build();
		defaultScheduler.scheduleJob(job1,trig1);
		defaultScheduler.getListenerManager()
				.addJobListener(new CustomJobListener(), EverythingMatcher.allJobs());
		defaultScheduler.getListenerManager()
				.addTriggerListener(new CustomTriggerListener());
		defaultScheduler.start();
	}
}

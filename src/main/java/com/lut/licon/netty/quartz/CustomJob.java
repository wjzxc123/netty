package com.lut.licon.netty.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/27 14:14
 */
@Slf4j
//执行完成后会重新存储JobDataMap的数据
@PersistJobDataAfterExecution
public class CustomJob implements Job {
	@Override
	public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		//通过JobExecutionContext获取JobDetail
		JobDetail jobDetail = jobExecutionContext.getJobDetail();
		JobKey jobKey = jobDetail.getKey();
		String jobDetailName = jobKey.getName();
		String jobDetailGroup = jobKey.getGroup();
		String jobClazzNameJobDetail = jobKey.getClass().getName();
		log.info("任务实例的唯一标识名称：" + jobDetailName);
		log.info("任务实例的组名：" + jobDetailGroup);
		log.info("任务实例绑定的任务类信息：" + jobClazzNameJobDetail);
		log.info("-----------------------------------------");
		//通过JobExecutionContext获取Trigger
		Trigger trigger = jobExecutionContext.getTrigger();
		TriggerKey triggerKey = trigger.getKey();
		String triggerKeyName = triggerKey.getName();
		String triggerKeyGroup = triggerKey.getGroup();
		String triggerClazzName = triggerKey.getClass().getName();
		log.info("触发器的唯一标识名称：" + triggerKeyName);
		log.info("触发器的组名：" + triggerKeyGroup);
		log.info("触发器绑定的任务类信息：" + triggerClazzName);
		log.info("-----------------------------------------");
		//通过JobExecutionContext
		String jobClazzName = jobExecutionContext.getClass().getName();
		log.info("job类相关的信息："+jobClazzName);
		log.info("-----------------------------------------");
		//获取JobDetail中JobDataMap中的message内容  <<===============
		JobDataMap jobDataMap = jobDetail.getJobDataMap();
		String message = jobDataMap.getString("message");
		log.info("从JobDetail-JobDataMap中或到的message内容为：" + message);
		//获取Trigger中JobDataMap中的username内容   <<===============
		JobDataMap triggerJobDataMap = trigger.getJobDataMap();
		String username = triggerJobDataMap.getString("username");
		log.info("从Trigger-JobDataMap中获取到的username内容为：" + username);
		log.info("-----------------------------------------");
		log.info(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + " | 任务被执行了");
	}
}

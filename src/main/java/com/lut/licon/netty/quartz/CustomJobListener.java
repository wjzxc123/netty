package com.lut.licon.netty.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/27 15:41
 */
@Slf4j
public class CustomJobListener implements JobListener {

	@Override
	public String getName() {
		String name = this.getClass().getSimpleName();
		log.info("当前JobListener的名称为：" + name);
		return name;
	}

	@Override
	//被triggerListener否决时不执行
	public void jobToBeExecuted(JobExecutionContext context) {
		String name = context.getJobDetail().getJobClass().getName();
		log.info("当前Job的名称为：" + name + "，JobDetail将要被执行了...");
	}

	@Override
	//triggerListener否决时执行
	public void jobExecutionVetoed(JobExecutionContext context) {
		String name = context.getJobDetail().getJobClass().getName();
		log.info("当前Job的名称为：" + name + "，JobDetail将要被执，但被TriggerListener否决...");
	}

	@Override
	//被triggerListener否决时不执行
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String name = context.getJobDetail().getJobClass().getName();
		log.info("当前Job的名称为：" + name + "，JobDetail执行完成了...");
	}
}

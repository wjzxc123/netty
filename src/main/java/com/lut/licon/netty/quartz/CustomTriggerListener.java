package com.lut.licon.netty.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/27 15:53
 */
@Slf4j
public class CustomTriggerListener implements TriggerListener {

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void triggerFired(Trigger trigger, JobExecutionContext context) {
		String name = trigger.getKey().getName();
		log.info(name + " | Job上的execute()方法将被执行时，Scheduler就调用该方法");
	}

	@Override
	public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
		boolean temp = true;
		String name = trigger.getKey().getName();
		log.info(name + " | Job将要被执行由Scheduler调用这个方法。Trigger Listener给了一个选择去否决Job的执行为" + (temp ? "为此次Trigger触发" : "不为此次Trigger触发"));
		return temp;
	}

	@Override
	public void triggerMisfired(Trigger trigger) {
		String name = trigger.getKey().getName();
		log.info(name + " ｜ scheduler调用这个方法是在Trigger错过触发时调用该方法");
	}

	@Override
	//被否决后将不执行
	public void triggerComplete(Trigger trigger, JobExecutionContext context, Trigger.CompletedExecutionInstruction triggerInstructionCode) {
		String name = trigger.getKey().getName();
		log.info(name + " | Trigger被触发并完成了Job的执行，scheduler调用这个方法");
	}
}

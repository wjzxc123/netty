package com.lut.licon.netty.quartz.example;

import com.lut.licon.netty.quartz.job.AbstractSafeJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/28 10:14
 */
@Component
@Slf4j
public class HelloJob extends AbstractSafeJob {
	@Override
	public void executeSafeJob(JobExecutionContext context) {
		String rtx = context.getJobDetail().getJobDataMap().getString("rtx");
		System.out.println(rtx);
	}
}

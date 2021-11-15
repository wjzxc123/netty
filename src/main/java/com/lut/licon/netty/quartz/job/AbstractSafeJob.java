package com.lut.licon.netty.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/28 10:06
 */
@Slf4j
public abstract class AbstractSafeJob extends AbstractJob{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			executeSafeJob(context);
		}catch (Exception e){
			log.error("任务执行异常："+e.getMessage());
			throw new JobExecutionException(e.getMessage());
		}
	}

	public abstract void executeSafeJob(JobExecutionContext context);
}

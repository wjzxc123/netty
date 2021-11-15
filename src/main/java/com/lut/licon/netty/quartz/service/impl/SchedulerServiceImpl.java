package com.lut.licon.netty.quartz.service.impl;

import com.lut.licon.netty.quartz.service.SchedulerService;
import org.quartz.Scheduler;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/28 10:19
 */
public class SchedulerServiceImpl implements SchedulerService {
	@Autowired
	Scheduler scheduler;
}

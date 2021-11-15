package com.lut.licon.netty.kafka.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/26 15:30
 */
@EnableScheduling
@ControllerAdvice
public class Timer {

	/**
	 * KafkaListener注解所标注的方法并不会在IOC容器中被注册为Bean，
	 * 而是会被注册在KafkaListenerEndpointRegistry中，
	 * 而KafkaListenerEndpointRegistry在SpringIOC中已经被注册为Bean
	 **/
	@Autowired
	private KafkaListenerEndpointRegistry registry;


	// 定时启动监听器
	//@Scheduled(cron = "0 13 16 * * ?")
	public void startListener() {
		System.out.println("启动监听器...");
		// "timingConsumer"是@KafkaListener注解后面设置的监听器ID,标识这个监听器
		if (!registry.getListenerContainer("consumer3").isRunning()) {
			registry.getListenerContainer("consumer3").start();
		}
		if (!registry.getListenerContainer("consumer2").isRunning()) {
			registry.getListenerContainer("consumer2").start();
		}
		if (!registry.getListenerContainer("consumer1").isRunning()) {
			registry.getListenerContainer("consumer1").start();
		}
		//registry.getListenerContainer("timingConsumer").resume();
	}

	// 定时停止监听器
	//@Scheduled(cron = "0 14 16 * * ?")
	public void shutDownListener() {
		System.out.println("关闭监听器...");
		registry.getListenerContainer("consumer1").pause();
		registry.getListenerContainer("consumer2").pause();
		registry.getListenerContainer("consumer3").pause();
	}
}

package com.lut.licon.netty.kafka.configure;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/15 9:43
 */
//@Configuration
public class KafkaConfiguration {

	@Autowired
	private ConsumerFactory consumerFactory;

	// 监听器容器工厂(设置禁止KafkaListener自启动)
	//@Bean
	public ConcurrentKafkaListenerContainerFactory delayContainerFactory() {
		ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
		container.setConsumerFactory(consumerFactory);
		//禁止KafkaListener自启动
		container.setAutoStartup(false);
		container.setBatchListener(true);
		return container;
	}

	//@Bean
	public NewTopic initTopic(){
		return new NewTopic("topic1",8,(short)2);
	}

	// 如果要修改分区数，只需修改配置值重启项目即可
	// 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
	//@Bean
	public NewTopic updateTopic() {
		return new NewTopic("topic1",10,(short)2);
	}

}

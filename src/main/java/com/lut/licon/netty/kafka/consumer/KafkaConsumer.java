package com.lut.licon.netty.kafka.consumer;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.stereotype.Component;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/15 10:26
 */
//@Component
public class KafkaConsumer {

	@Bean
	public ConsumerAwareListenerErrorHandler consumerAwareListenerErrorHandler(){
		return (message, exception, consumer) -> {
			System.out.println("消费异常:"+message.getPayload());
			return null;
		};
	}
	@KafkaListener(id = "consumer1",containerFactory = "delayContainerFactory",topics = "topic2",groupId = "licon-family",errorHandler = "consumerAwareListenerErrorHandler")
	public void consumer1Message(List<ConsumerRecord<?,?>> records){
		for (ConsumerRecord<?, ?> record : records) {
			System.out.println(record.topic()+","+record.partition()+","+record.value());
		}
	}

	@KafkaListener(id = "consumer2",containerFactory = "delayContainerFactory",topics = "topic3",groupId = "licon-family",errorHandler = "consumerAwareListenerErrorHandler")
	public void consumer2Message(List<ConsumerRecord<?,?>> records)throws Exception{
		System.out.println("批量消费");
		throw new Exception("批量消费-模拟异常");
	}

	@KafkaListener(id = "consumer3",containerFactory = "delayContainerFactory",groupId = "licon-only",topicPartitions = {
			@TopicPartition(topic = "topic1",
					partitionOffsets ={
							@PartitionOffset(partition = "0",initialOffset = "0"),
							@PartitionOffset(partition = "1",initialOffset = "0")
					}
			)
	},errorHandler = "consumerAwareListenerErrorHandler")
	public void consumerPartitionMessage(List<ConsumerRecord<?,?>> records){
		for (ConsumerRecord<?, ?> record : records) {
			System.out.println(record.topic()+","+record.partition()+","+record.offset()+","+record.value());
		}
	}
}

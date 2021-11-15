package com.lut.licon.netty.kafka.product;


import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Describe:
 *
 * @author Licon
 * @date 2021/9/15 10:06
 */
@RestController
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	@GetMapping("/kafka/product/{message}")
	@Transactional
	public void sendMessage(@PathVariable("message")String message){
		kafkaTemplate.send("topic1",message);
	}

	@GetMapping("/kafka/call-product/{message}")
	@Transactional
	public void sendMessageCall(@PathVariable("message")String message){
		kafkaTemplate.send("topic1",message).addCallback(success->{
			String topic = Objects.requireNonNull(success).getRecordMetadata().topic();
			int partition = Objects.requireNonNull(success).getRecordMetadata().partition();
			long offset = Objects.requireNonNull(success).getRecordMetadata().offset();
			System.out.println("发送成功:"+topic+"-"+partition+"-"+offset);
		},failure->{
			System.out.println("发送失败:"+failure.getMessage());
		});
	}

	@GetMapping("/kafka/tra-product/{message}")
	@Transactional
	public void sendMessageTransaction(@PathVariable("message")String message){
		kafkaTemplate.executeInTransaction(operations -> {
			try {
				SendResult<String, Object> result = null;
				try {
					result = operations.send("topic1", message).get();
				}catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
				return message;
			}catch (Exception e){
				e.printStackTrace();
				return "发送失败";
			}
		});

		/*kafkaTemplate.send("topic1","test executeInTransaction");
		throw new RuntimeException("fail");*/
	}
}

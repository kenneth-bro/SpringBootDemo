package com.kenneth.boot.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.kenneth.boot.config.RabbitConfig;


/**
 * 队列消费者
 * @author liq
 * @date 2018年2月28日
 */
@Component
public class QueueConsumerService {
	
	Logger logger = LoggerFactory.getLogger(QueueConsumerService.class);
	
	@RabbitListener(queues = RabbitConfig.QUEUE_NAME)
	public void consumeMessage(Map<String, Object> map){
		logger.info("消费:[" + map.get("num") + "]");
	}
}

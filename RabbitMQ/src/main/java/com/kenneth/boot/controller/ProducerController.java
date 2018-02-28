package com.kenneth.boot.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenneth.boot.config.RabbitConfig;
/**
 * 消息队列生产者
 * @author liq
 * @date 2018年2月28日
 */
@RestController
public class ProducerController {
	
	Logger logger = LoggerFactory.getLogger(ProducerController.class);
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@RequestMapping("/sendMessage")
	public Object sendMessage(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0; i < 100; i++){
					Map<String, Object> subMap = new HashMap<String, Object>();
					subMap.put("num", i);
					rabbitTemplate.convertAndSend(RabbitConfig.EXCHANEG_NAME, RabbitConfig.ROUTING_KEY, subMap);
				}
				
			}
		}).start();
		return "ok";
	}
	
}

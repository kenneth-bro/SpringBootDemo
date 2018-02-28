package com.kenneth.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
	//队列名称
	public final static String QUEUE_NAME = "test-queue";
	
	//交互器名称
	public final static String EXCHANEG_NAME = "test-exchange";
	
	//路由键
	public final static String ROUTING_KEY = "text-key";
	
	//创建队列
	@Bean
	public Queue queue(){
		return new Queue(QUEUE_NAME);
	}
	
	//创建一个topic类型的交换器
	@Bean
	public TopicExchange exchange(){
		return new TopicExchange(EXCHANEG_NAME);
	}
	
	//使用路由键(route-key)把队列(queue)绑定到交换器(exchange)
	@Bean
	public Binding binding(Queue queue, TopicExchange exchange){
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
	
	@Bean
	public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost("192.168.12.152");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        //保证消息的事务性处理rabbitmq默认的处理方式为autoack ，这意味着当你从消息队列取出一个消息时，ack自动发送，mq就会将消息删除。而为了保证消息的正确处理，我们需要将消息处理修改为手动确认的方式
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		return new RabbitTemplate(connectionFactory);
	}
}

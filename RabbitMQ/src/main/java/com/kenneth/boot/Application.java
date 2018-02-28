package com.kenneth.boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableDiscoveryClient
@EnableFeignClients
@EnableZuulProxy
@SpringBootApplication
public class Application extends SpringBootServletInitializer{

	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		new SpringApplicationBuilder(Application.class).web(true).run(args);
		System.out.println("RabbitMQ start");
	}
	
}

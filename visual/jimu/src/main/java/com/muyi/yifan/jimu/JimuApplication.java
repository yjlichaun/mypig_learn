package com.muyi.yifan.jimu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author pig archetype
 * <p>
 * 项目启动类
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = { MongoAutoConfiguration.class })
public class JimuApplication {

	public static void main(String[] args) {
		SpringApplication.run(JimuApplication.class, args);
	}

}

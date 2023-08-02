package com.muyi.admin;

import com.muyi.common.feign.annotation.EnableMyPigFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户统一管理系统
 * @author 历川 
 * @date 2023-08-02 21:08:40
 */
@SpringBootApplication
@EnableMyPigFeignClient
@EnableDiscoveryClient
public class AdminApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
    
}

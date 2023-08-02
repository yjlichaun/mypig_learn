package com.muyi.common.feign.sentinel;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.muyi.common.feign.sentinel.ext.MyPigSentinelFeign;
import com.muyi.common.feign.sentinel.handle.MyPigUrlBlockHandler;
import com.muyi.common.feign.sentinel.parser.MyPigHeaderRequestOriginParser;
import com.ulisesbocchio.jasyptspringboot.annotation.ConditionalOnMissingBean;
import feign.Feign;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author 历川
 * @version 1.0
 * @description sentinel 配置
 * @date 2023/8/2 9:23
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class SentinelAutoConfiguration {
    
    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "feign.sentinel.enabled")
    public Feign.Builder feignSentinelBuilder() { return MyPigSentinelFeign.builder(); }
    
    @Bean
    @ConditionalOnMissingBean
    public BlockExceptionHandler blockExceptionHandler() { return new MyPigUrlBlockHandler();}
    
    @Bean
    @ConditionalOnMissingBean
    public RequestOriginParser requestOriginParser() { return new MyPigHeaderRequestOriginParser(); }
}

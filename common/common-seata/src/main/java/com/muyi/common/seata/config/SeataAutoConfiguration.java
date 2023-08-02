package com.muyi.common.seata.config;

import com.muyi.common.core.factory.YamlPropertySourceFactory;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 历川
 * @version 1.0
 * @description Seata配置类
 * @date 2023/8/2 20:50
 */
@PropertySource(value = "classpath:seata-config.yml",factory = YamlPropertySourceFactory.class)
@EnableAutoDataSourceProxy
@Configuration(proxyBeanMethods = false)
public class SeataAutoConfiguration {
}

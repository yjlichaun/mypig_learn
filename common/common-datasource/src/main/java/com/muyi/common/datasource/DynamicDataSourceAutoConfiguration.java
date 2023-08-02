package com.muyi.common.datasource;

import com.baomidou.dynamic.datasource.processor.DsProcessor;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.muyi.common.datasource.config.DataSourceProperties;
import com.muyi.common.datasource.config.JdbcDynamicDataSourceProvider;
import com.muyi.common.datasource.config.LastParamDsProcessor;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 历川
 * @version 1.0
 * @description 动态数据源切换配置
 * @date 2023/8/2 20:57
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DynamicDataSourceAutoConfiguration {
    @Bean
    public DynamicDataSourceProvider dynamicDataSourceProvider(StringEncryptor stringEncryptor,
                                                               DataSourceProperties properties) {
        return new JdbcDynamicDataSourceProvider(stringEncryptor, properties);
    }
    
    @Bean
    public DsProcessor dsProcessor() {
        return new LastParamDsProcessor();
    }
    
}

package com.muyi.common.redis.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 历川
 * @version 1.0
 * @description redis配置属性
 * @date 2023/8/2 20:14
 */

@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfigProperties {
    private String password;
    
    private String database;
    
    private int port = 6379;
    
    private String host;
}

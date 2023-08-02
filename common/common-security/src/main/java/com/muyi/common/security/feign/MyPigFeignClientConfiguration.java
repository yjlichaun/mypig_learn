package com.muyi.common.security.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @author 历川
 * @version 1.0
 * @description 注入 oauth2 feign token 增强
 * @date 2023/8/2 20:47
 */

public class MyPigFeignClientConfiguration {
    /**
     * 注入 oauth2 feign token 增强
     * @return 拦截器
     */
    @Bean
    public RequestInterceptor oauthRequestInterceptor() {
        return new MyPigOAuthRequestInterceptor();
    }
    
}


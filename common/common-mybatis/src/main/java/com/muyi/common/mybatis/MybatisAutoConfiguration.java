package com.muyi.common.mybatis;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.muyi.common.mybatis.config.MybatisPlusMetaObjectHandler;
import com.muyi.common.mybatis.plugins.DecryptFieldInterceptor;
import com.muyi.common.mybatis.plugins.EncryptFieldInterceptor;
import com.muyi.common.mybatis.plugins.MyPigPaginationInnerInterceptor;
import com.muyi.common.mybatis.resolver.SqlFilterArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description mybatis plus 统一配置
 * @date 2023/8/2 20:20
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class MybatisAutoConfiguration implements WebMvcConfigurer {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> {
            // 注入参数入库加解密拦截器
            configuration.addInterceptor(new EncryptFieldInterceptor());
            configuration.addInterceptor(new DecryptFieldInterceptor());
        };
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
    }
    
    /**
     * SQL 过滤器避免SQL 注入
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SqlFilterArgumentResolver());
    }
    
    /**
     * 分页插件, 对于单一数据库类型来说,都建议配置该值,避免每次分页都去抓取数据库类型
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new MyPigPaginationInnerInterceptor());
        return interceptor;
    }
    
    /**
     * 审计字段自动填充
     * @return {@link MetaObjectHandler}
     */
    @Bean
    public MybatisPlusMetaObjectHandler mybatisPlusMetaObjectHandler() {
        return new MybatisPlusMetaObjectHandler();
    }
    
}

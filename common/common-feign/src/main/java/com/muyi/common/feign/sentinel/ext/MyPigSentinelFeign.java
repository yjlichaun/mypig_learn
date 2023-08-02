package com.muyi.common.feign.sentinel.ext;

import com.alibaba.cloud.sentinel.feign.SentinelContractHolder;
import feign.Contract;
import feign.Feign;
import feign.InvocationHandlerFactory;
import feign.Target;
import org.aspectj.apache.bcel.generic.FieldGen;
import org.springframework.beans.BeansException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author 历川
 * @version 1.0
 * @description 支持自动降级注入 重写 {@link com.alibaba.cloud.sentinel.feign.SentinelFeign}
 * @date 2023/8/2 9:30
 */

public final class MyPigSentinelFeign {
    
    private MyPigSentinelFeign() {
    }
    
    public static MyPigSentinelFeign.Builder builder() {
        return new MyPigSentinelFeign.Builder();
    }
    
    public static final class Builder extends Feign.Builder implements ApplicationContextAware {
        
        private Contract contract = new Contract.Default();
        
        private ApplicationContext applicationContext;
        
        private FeignContext feignContext;
        
        @Override
        public Feign.Builder invocationHandlerFactory(InvocationHandlerFactory invocationHandlerFactory) {
            throw new UnsupportedOperationException();
        }
        
        @Override
        public MyPigSentinelFeign.Builder contract(Contract contract) {
            this.contract = contract;
            return this;
        }
        
        @Override
        public Feign build() {
            super.invocationHandlerFactory(new InvocationHandlerFactory() {
                
                @Override
                public InvocationHandler create(Target target, Map<Method, MethodHandler> dispatch) {
                    
                    //查找FeignClient上的 降级策略
                    FeignClient feignClient = AnnotationUtils.findAnnotation(target.type(), FeignClient.class);
                    Class<?> fallback = feignClient.fallback();
                    Class<?> fallbackFactory = feignClient.fallbackFactory();
                    
                    String beanName = feignClient.contextId();
                    if (!StringUtils.hasText(beanName)) {
                        beanName = feignClient.name();
                    }
                    
                    Object fallbackInstance;
                    FallbackFactory<?> fallbackFactoryInstance;
                    if (void.class != fallback) {
                        fallbackInstance = getFromContext(beanName,"fallback",fallback,target.type());
                        return new MyPigSentinelInvocationHandler(target,
                                dispatch,new FallbackFactory.Default(fallbackInstance));
                    }
                    
                    if (void.class != fallbackFactory) {
                        fallbackFactoryInstance = (FallbackFactory<?>) getFromContext(beanName,"fallbackFactory",
                                                fallbackFactory,FallbackFactory.class);
                        return new MyPigSentinelInvocationHandler(target,dispatch,fallbackFactoryInstance);
                    }
                    return new MyPigSentinelInvocationHandler(target,dispatch);
                }
                
                private Object getFromContext(String beanName,String type,Class<?> fallbackType,Class<?> targetType) {
                    Object fallbackInstance = feignContext.getInstance(beanName,fallbackType);
                    if (fallbackInstance == null) {
                        throw new IllegalStateException(String.format(
                                "no %s instance of type %s found for feign client %s", type, fallbackType, beanName));
                    }
                    
                    if (!targetType.isAssignableFrom(fallbackType)) {
                        throw new IllegalStateException(String.format(
                                "Incompatible %s instance. Fallback/fallbackFactory of type %s is not assignable to %s for feign client %s",
                                type, fallbackType, targetType, beanName));
                    }
                    return fallbackInstance;
                }
                
            });
            super.contract(new SentinelContractHolder(contract));
            return super.build();
        }
        private Object getFieldValue(Object instance, String fieldName) {
            Field field = ReflectionUtils.findField(instance.getClass(), fieldName);
            field.setAccessible(true);
            try {
                return field.get(instance);
            }
            catch (IllegalAccessException e) {
                //ignore
            }
            return null;
        }
        
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
            feignContext = this.applicationContext.getBean(FeignContext.class);
        }
    }
}

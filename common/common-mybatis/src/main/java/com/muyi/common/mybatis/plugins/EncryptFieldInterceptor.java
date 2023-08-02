package com.muyi.common.mybatis.plugins;

import com.muyi.common.mybatis.anon.EncryptDecryptData;
import com.muyi.common.mybatis.utils.EncryptDecrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;

/**
 * @author 历川
 * @version 1.0
 * @description 字段加密
 * @date 2023/8/2 20:22
 */
@Intercepts({@Signature(type = ParameterHandler.class, method = "setParameters", args = {PreparedStatement.class})})
@Slf4j
public class EncryptFieldInterceptor implements Interceptor {
    
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
        parameterField.setAccessible(true);
        // 取出实例
        Object parameterObject = parameterField.get(parameterHandler);
        if (parameterObject != null) {
            Class<?> parameterObjectClass = parameterObject.getClass();
            // 校验该实例的类是否被@EncryptDecryptData所注解
            EncryptDecryptData encryptDecryptData = AnnotationUtils.findAnnotation(parameterObjectClass,
                    EncryptDecryptData.class);
            if (Objects.nonNull(encryptDecryptData)) {
                // 取出当前当前类所有字段，传入加密方法
                Field[] declaredFields = parameterObjectClass.getDeclaredFields();
                EncryptDecrypt.encrypt(declaredFields, parameterObject);
            }
            
        }
        return invocation.proceed();
    }
    
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    
}

package com.muyi.common.log.annotation;

import java.lang.annotation.*;

/**
 * @author 历川
 * @description 操作日志注解
 * @date 2023/8/1 15:34
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    /**
     * 描述
     * @return {String}
     */
    String value() default "";
    
    /**
     * spel 表达式
     * @return 日志描述
     */
    String expression() default "";
    
}

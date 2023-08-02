package com.muyi.common.core.util;

import cn.hutool.extra.spring.SpringUtil;
import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author 历川
 * @version 1.0
 * @description i18n 工具类
 * @date 2023/8/2 22:05
 */
@UtilityClass
public class MsgUtils {
    /**
     * 通过code 获取中文错误信息
     * @param code
     * @return
     */
    public String getMessage(String code) {
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, null, Locale.CHINA);
    }
    
    /**
     * 通过code 和参数获取中文错误信息
     * @param code
     * @return
     */
    public String getMessage(String code, Object... objects) {
        MessageSource messageSource = SpringUtil.getBean("messageSource");
        return messageSource.getMessage(code, objects, Locale.CHINA);
    }
    
}

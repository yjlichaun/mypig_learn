package com.muyi.common.core.constant;

/**
 * @author 历川
 * @description 缓存key的常量
 * @date 2023/8/1 15:40
 */
public interface CacheConstants {
    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY:";
    
    /**
     * 菜单信息缓存
     */
    String MENU_DETAILS = "menu_details";
    
    /**
     * 用户信息缓存
     */
    String USER_DETAILS = "user_details";
    
    /**
     * 字典信息缓存
     */
    String DICT_DETAILS = "dict_details";
    
    /**
     * 参数缓存
     */
    String PARAMS_DETAILS = "params_details";
    
    /**
     * 登录用户 redis key
     */
    String LOGIN_TOKEN_KEY = "Authorization:login:token:";
    
    /**
     * 在线用户 redis key
     */
    String ONLINE_TOKEN_KEY = "online_tokens:";
    
}

package com.muyi.common.core.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 历川
 * @version 1.0
 * @description TODO
 * @date 2023/8/1 15:37
 */
@Data
@NoArgsConstructor
public class SysUserOnline implements Serializable {
    /**
     * 会话编号
     */
    private String tokenId;
    
    /**
     * 用户名称
     */
    private String userName;
    
    /**
     * 用户类型
     */
    private String userType;
    
    /**
     * 浏览器类型
     */
    private String browser;
    
    /**
     * 操作系统
     */
    private String os;
    
    /**
     * 登录IP地址
     */
    private String ipaddr;
    
    /**
     * 登录时间
     */
    private Date loginTime;
    
    /**
     * token 剩余有效时间 (单位: 秒)
     **/
    private long tokenTimeout;
    
}

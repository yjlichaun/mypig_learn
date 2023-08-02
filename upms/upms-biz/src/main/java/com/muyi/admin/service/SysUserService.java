package com.muyi.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyi.admin.api.entity.SysUser;
import com.muyi.common.core.dto.LoginUser;
import org.springframework.stereotype.Service;

/**
 * 用户服务接口
 *
 * @author 历川
 * @date 2023-08-02 21:47:26
 */
public interface SysUserService extends IService<SysUser> {
    
    /**
     * 查询用户信息
     * @param sysUser 用户
     * @return userInfo
     */
    LoginUser getUserInfo(SysUser sysUser);
}

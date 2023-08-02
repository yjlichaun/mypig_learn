package com.muyi.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyi.admin.api.entity.SysUser;
import com.muyi.admin.mapper.SysUserMapper;
import com.muyi.admin.service.SysUserService;
import com.muyi.common.core.constant.enums.UserType;
import com.muyi.common.core.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 历川
 * @version 1.0
 * @description 用户接口实现
 * @date 2023/8/2 21:48
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {
    @Override
    public LoginUser getUserInfo(SysUser sysUser) {
        LoginUser userInfo = new LoginUser();
        userInfo.setUserId(sysUser.getUserId());
        userInfo.setUsername(sysUser.getUsername());
        userInfo.setDeptId(sysUser.getDeptId());
        userInfo.setUserType(UserType.SYS_USER.getUserType());
        userInfo.setPassword(sysUser.getPassword());
        //TODO:2023-08-02 22:15:21
        return null;
    }
}

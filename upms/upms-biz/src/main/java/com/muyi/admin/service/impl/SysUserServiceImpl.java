package com.muyi.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyi.admin.api.entity.SysMenu;
import com.muyi.admin.api.entity.SysPost;
import com.muyi.admin.api.entity.SysRole;
import com.muyi.admin.api.entity.SysUser;
import com.muyi.admin.mapper.SysPostMapper;
import com.muyi.admin.mapper.SysRoleMapper;
import com.muyi.admin.mapper.SysUserMapper;
import com.muyi.admin.service.SysMenuService;
import com.muyi.admin.service.SysUserService;
import com.muyi.common.core.constant.enums.MenuTypeEnum;
import com.muyi.common.core.constant.enums.UserType;
import com.muyi.common.core.dto.LoginUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    
    @Autowired
    SysRoleMapper sysRoleMapper;
    
    @Autowired
    SysPostMapper sysPostMapper;
    
    @Autowired
    SysMenuService sysMenuService;
    @Override
    public LoginUser getUserInfo(SysUser sysUser) {
        LoginUser userInfo = new LoginUser();
        userInfo.setUserId(sysUser.getUserId());
        userInfo.setUsername(sysUser.getUsername());
        userInfo.setDeptId(sysUser.getDeptId());
        userInfo.setUserType(UserType.SYS_USER.getUserType());
        userInfo.setPassword(sysUser.getPassword());
        //设置角色列表
        List<SysRole> roleList =  sysRoleMapper.listRolesByUserId(sysUser.getUserId());
        userInfo.setRoleList(roleList);
        //设置角色列表id
        List<Long> roleIds = roleList.stream().map(SysRole::getRoleId).collect(Collectors.toList());
        userInfo.setRoles(roleList.stream().map(SysRole::getRoleCode).collect(Collectors.toList()));
        userInfo.setRoleIds(roleIds);
        //设置岗位集合
        List<SysPost> postList = sysPostMapper.listPostsByUserId(userInfo.getUserId());
        userInfo.setPostList(postList);
        //设置权限集合列表(menu.permission)
        Set<String> permissions = roleIds.stream().map(sysMenuService::findMenuByRoleId).flatMap(Collection::stream)
                .filter(m -> MenuTypeEnum.BUTTON.getType().equals(m.getType())).map(SysMenu::getPermission)
                .filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        userInfo.setMenuPermissions(new ArrayList<>(permissions));
        return userInfo;
    }
}

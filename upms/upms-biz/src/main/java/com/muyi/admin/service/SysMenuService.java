package com.muyi.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muyi.admin.api.entity.SysMenu;

import java.util.Set;

/**
 * 菜单权限列表 服务类
 *
 * @author 历川
 * @date 2023-08-03 21:00:36
 */
public interface SysMenuService extends IService<SysMenu> {
    
    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    Set<SysMenu> findMenuByRoleId(Long roleId);
}

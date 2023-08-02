package com.muyi.common.satoken.core.service;

import cn.dev33.satoken.stp.StpInterface;
import com.muyi.common.satoken.utils.LoginHelper;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description sa-token 权限管理实现类
 * @date 2023/8/2 20:08
 */

public class SaPermissionImpl implements StpInterface {
    
    /**
     * 获取菜单权限列表
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return LoginHelper.getLoginUser().getMenuPermissions();
    }
    /**
     * 获取角色权限列表
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return LoginHelper.getLoginUser().getRoles();
    }
}

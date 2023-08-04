package com.muyi.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muyi.admin.api.entity.SysMenu;
import com.muyi.admin.mapper.SysMenuMapper;
import com.muyi.admin.service.SysMenuService;
import com.muyi.common.core.constant.CacheConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author 历川
 * @version 1.0
 * @description 菜单权限表 服务实现类
 * @date 2023/8/3 21:01
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Override
    @Cacheable(value = CacheConstants.MENU_DETAILS, key = "#roleId  + '_menu'", unless = "#result == null")
    public Set<SysMenu> findMenuByRoleId(Long roleId) {
        return baseMapper.listMenusByRoleId(roleId);
    }
}

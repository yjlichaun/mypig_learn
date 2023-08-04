package com.muyi.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyi.admin.api.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * 菜单权限表 Mapper 接口
 * @author 历川
 * @date 2023-08-03 21:02:51
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过角色编号查询菜单
     * @param roleId 角色ID
     * @return
     */
    Set<SysMenu> listMenusByRoleId(Long roleId);
}

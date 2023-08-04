package com.muyi.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyi.admin.api.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户角色mapper接口
 * @author 历川
 * @date 2023-08-03 20:34:17
 */

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    
    
    /**
     * 通过用户ID查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> listRolesByUserId(Long userId);
}

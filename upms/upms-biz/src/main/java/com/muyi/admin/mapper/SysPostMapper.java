package com.muyi.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muyi.admin.api.entity.SysPost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 岗位管理表 mapper接口
 * @date 2023/8/3 20:54
 */

@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {
    
    /**
     * 通过用户ID，查询岗位信息
     * @param userId 用户id
     * @return 岗位信息
     */
    List<SysPost> listPostsByUserId(Long userId);
}

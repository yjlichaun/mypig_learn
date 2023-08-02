package com.muyi.admin.api.feign;

import com.muyi.common.core.constant.ServiceNameConstants;
import com.muyi.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 查询关系树
 * @date 2023/8/2 10:44
 */
@FeignClient(contextId = "remoteDeptService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteDeptService {
    
    
    /**
     * 查询子级id列表
     * @param deptId 子级id
     * @return 子级id列表
     */
    @GetMapping("/dept/child-id/{deptId}")
    R<List<Long>> listChildDeptId(@PathVariable("deptId") Long deptId);
}

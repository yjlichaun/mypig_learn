package com.muyi.admin.api.feign;

import com.muyi.admin.api.entity.SysDictItem;
import com.muyi.common.core.constant.ServiceNameConstants;
import com.muyi.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @description 查询参数相关
 * @author 历川
 * @date 2023-08-02 09:05:26
 */
@FeignClient(contextId = "remoteDictService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteDictService {
    
    /**
     * 通过字典类型查找字典
     * @param type 字典类型
     * @return 同类型字典
     */
    @GetMapping("/dict/type/{type}")
    R<List<SysDictItem>> getDictByType(@PathVariable("type") String type);
}

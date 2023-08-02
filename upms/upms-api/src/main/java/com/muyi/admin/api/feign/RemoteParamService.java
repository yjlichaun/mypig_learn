package com.muyi.admin.api.feign;

import com.muyi.common.core.constant.ServiceNameConstants;
import com.muyi.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 历川
 * @version 1.0
 * @description 参数查询相关
 * @date 2023/8/2 10:50
 */
@FeignClient(contextId = "remoteParamService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteParamService {
    
    /**
     * 通过key获取参数配置
     * @param key key
     * @return 参数
     */
    @GetMapping("/param/publicValue/{key}")
    R<String> getByKey(@PathVariable("key") String key);
}

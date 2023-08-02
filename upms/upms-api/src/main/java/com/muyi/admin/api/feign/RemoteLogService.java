package com.muyi.admin.api.feign;

import com.muyi.admin.api.entity.SysLog;
import com.muyi.common.core.constant.ServiceNameConstants;
import com.muyi.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description 日志操作
 * @author 历川
 * @date  2023-08-02 10:48:01
 */

@FeignClient(contextId = "remoteLogService",value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteLogService {
    
    
    /**
     * 保存日志
     * @param sysLog 系统日志
     * @return 是否保存成功
     */
    @PostMapping("/log")
    R<Boolean> saveLog(@RequestBody SysLog sysLog);
}

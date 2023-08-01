package com.muyi.common.log.event;

import com.muyi.admin.api.entity.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @author 历川
 * @version 1.0
 * @description TODO
 * @date 2023/8/1 15:31
 */
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(SysLog source) {
        super(source);
    }
}

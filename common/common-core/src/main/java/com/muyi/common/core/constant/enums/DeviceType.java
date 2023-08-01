package com.muyi.common.core.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 历川
 * @version 1.0
 * @description 用户设备类型
 * @date 2023/8/1 15:16
 */
@Getter
@AllArgsConstructor
public enum DeviceType {
    /**
     * pc端
     */
    PC("PC");
    
    private final String device;
}

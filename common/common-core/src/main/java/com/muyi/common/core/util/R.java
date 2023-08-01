package com.muyi.common.core.util;

import com.muyi.common.core.constant.CommonConstants;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 历川
 * @version 1.0
 * @description TODO
 * @date 2023/8/1 15:06
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Getter
    @Setter
    private int code;
    
    @Getter
    @Setter
    private String msg;
    
    @Getter
    @Setter
    private T data;
    
    public static <T> R<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, null);
    }
    
    public static <T> R<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, null);
    }
    
    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }
    
    public static <T> R<T> failed() {
        return restResult(null, CommonConstants.FAIL, null);
    }
    
    public static <T> R<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }
    
    public static <T> R<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }
    
    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }
    
    public static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }
    
    public static <T> R<T> restResult(int code, String msg) {
        return restResult(null, code, msg);
    }
}

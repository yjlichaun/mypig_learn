package com.muyi.common.feign.sentinel.parser;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 历川
 * @version 1.0
 * @description sentinel 请求头解析判断
 * @date 2023/8/2 10:19
 */
public class MyPigHeaderRequestOriginParser implements RequestOriginParser {
    /**
     * 请求头获取allow
     */
    private static final String ALLOW = "Allow";
    
    /**
     * Parse the origin from given HTTP request.
     * @param request HTTP request
     * @return parsed origin
     */
    @Override
    public String parseOrigin(HttpServletRequest request) {
        return request.getHeader(ALLOW);
    }
}

package com.muyi.common.security.feign;

import cn.dev33.satoken.id.SaIdUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 历川
 * @version 1.0
 * @description
 * oauth2 feign token传递
 * 重新 OAuth2FeignRequestInterceptor ，官方实现部分常见不适用
 * @date 2023/8/2 20:47
 */
@Slf4j
@RequiredArgsConstructor
public class MyPigOAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header(SaIdUtil.ID_TOKEN, SaIdUtil.getToken());
    }
    
}

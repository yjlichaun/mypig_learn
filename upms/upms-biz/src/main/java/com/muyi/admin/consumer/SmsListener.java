package com.muyi.admin.consumer;

import com.muyi.admin.api.dto.SmsSendMessage;
import com.muyi.admin.service.SmsSendService;
import com.muyi.common.sms.core.client.SmsClientFactory;
import com.muyi.common.sms.core.property.SmsChannelProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author 历川
 * @version 1.0
 * @description 短信监听
 * @date 2023/8/2 21:10
 */
@Component
@AllArgsConstructor
public class SmsListener {
    private final SmsClientFactory smsClientFactory;
    
    private final SmsSendService smsSendService;
    
    /**
     * 短信渠道监听
     *
     * @author 王晨阳
     * @version 1.0
     * @date 2022/8/25 10:28
     * @desc
     **/
    @EventListener
    @Async  //异步执行 独立线程执行
    public void createOrUpdateSmsClientEvent(SmsChannelProperties smsChannelProperties) {
        if (StringUtils.isNotEmpty(smsChannelProperties.getApiKey())) {
            smsClientFactory.createOrUpdateSmsClient(smsChannelProperties);
        }
        else {
            smsClientFactory.removeSmsClient(smsChannelProperties.getId());
        }
        
    }
    
    /**
     * 发送短信
     *
     * @author 王晨阳
     * @version 1.0
     * @date 2022/8/25 10:28
     * @desc
     **/
    @EventListener
    @Async //异步执行 独立线程执行
    public void sendSmsSendMessage(SmsSendMessage message) {
        smsSendService.doSendSms(message);
    }
    
}

package com.muyi.common.sms.config;

import com.muyi.common.sms.core.client.SmsClientFactory;
import com.muyi.common.sms.core.client.impl.SmsClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信配置类
 */
@Configuration
public class SmsAutoConfiguration {

	@Bean
	public SmsClientFactory smsClientFactory() {
		return new SmsClientFactoryImpl();
	}

}

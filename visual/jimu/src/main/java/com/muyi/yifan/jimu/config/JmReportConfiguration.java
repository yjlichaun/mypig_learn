package com.muyi.yifan.jimu.config;

import com.muyi.yifan.jimu.service.JmReportTokenServiceImpl;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 积木报表的配置类
 *
 */
@Configuration
public class JmReportConfiguration {

	@Bean
	public JmReportTokenServiceI jmReportTokenService() {
		return new JmReportTokenServiceImpl();
	}

}

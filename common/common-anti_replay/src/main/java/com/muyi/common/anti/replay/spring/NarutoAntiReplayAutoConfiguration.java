package com.muyi.common.anti.replay.spring;

import com.muyi.common.anti.replay.annotation.EnableNarutoAntiReplay;
import online.inote.naruto.common.utils.bean.EnableBeanFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBeanFactory
@EnableNarutoAntiReplay
public class NarutoAntiReplayAutoConfiguration {

}

package com.muyi.admin.api.dto;


import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections4.KeyValue;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 短信发送消息
 *
 */
@Data
@Accessors(chain = true)
public class SmsSendMessage {

	/**
	 * 短信日志编号
	 */
	@NotNull(message = "短信日志编号不能为空")
	private Long logId;

	/**
	 * 手机号
	 */
	@NotNull(message = "手机号不能为空")
	private String mobile;

	/**
	 * 短信渠道编号
	 */
	@NotNull(message = "短信渠道编号不能为空")
	private Long channelId;

	/**
	 * 短信 API 的模板编号
	 */
	@NotNull(message = "短信 API 的模板编号不能为空")
	private String apiTemplateId;

	/**
	 * 短信模板参数
	 */
	private List<KeyValue<String, Object>> templateParams;

}

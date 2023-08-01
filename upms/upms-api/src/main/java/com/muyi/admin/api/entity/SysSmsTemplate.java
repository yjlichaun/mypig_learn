package com.muyi.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.muyi.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 短信模板
 *
 * @author 历川 
 * @date 2023-08-01 23:01:49
 */
@Data
@TableName("sys_sms_template")
@EqualsAndHashCode(callSuper = true)
public class SysSmsTemplate extends BaseEntity {

	/**
	 * 编号
	 */
	@TableId(type = IdType.AUTO)
	private Long id;

	/**
	 * 短信类型
	 */
	@NotNull(message = "短信类型不能为空")
	private Integer type;

	/**
	 * 开启状态
	 */
	@NotNull(message = "开启状态不能为空")
	private Integer status;

	/**
	 * 模板编码
	 */
	@NotNull(message = "模板编码不能为空")
	private String code;

	/**
	 * 模板名称
	 */
	@NotNull(message = "模板名称不能为空")
	private String name;

	/**
	 * 模板内容
	 */
	@NotNull(message = "模板内容不能为空")
	private String content;

	/**
	 * 参数数组
	 */
	private String params;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 短信 API 的模板编号
	 */
	@NotNull(message = "短信 API 的模板编号不能为空")
	private String apiTemplateId;

	/**
	 * 短信渠道编号
	 */
	@NotNull(message = "短信渠道编号不能为空")
	private Long channelId;

	/**
	 * 短信渠道编码
	 */
	private String channelCode;

	/**
	 * 0-正常，1-删除
	 */
	@TableLogic
	private String delFlag;

}

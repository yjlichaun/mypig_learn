package com.muyi.admin.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 历川 
 * @date 2023-08-01 23:03:31
 * <p>
 * 日志查询传输对象
 */
@Data
public class SysLogDTO {

	/**
	 * 查询日志类型
	 */
	private String type;

	/**
	 * 创建时间区间 [开始时间，结束时间]
	 */
	private LocalDateTime[] createTime;

}

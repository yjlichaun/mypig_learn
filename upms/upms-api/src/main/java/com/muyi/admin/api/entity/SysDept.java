/*
 * Copyright (c) 2020 yifan4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.muyi.admin.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.muyi.common.mybatis.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author 历川
 * @date 2023/8/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysDept extends BaseEntity {

	private static final long serialVersionUID = 1L;

	
	@TableId(value = "dept_id", type = IdType.ASSIGN_ID)
	private Long deptId;

	/**
	 * 部门名称
	 */
	@NotBlank(message = "部门名称不能为空")
	private String name;

	/**
	 * 排序
	 */
	@NotNull(message = "部门排序值不能为空")
	private Integer sortOrder;

	/**
	 * 父级部门id
	 */
	private Long parentId;

	/**
	 * 是否删除 -1：已删除 0：正常
	 */
	@TableLogic
	private String delFlag;

}

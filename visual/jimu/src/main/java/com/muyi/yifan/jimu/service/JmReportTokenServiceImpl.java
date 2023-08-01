package com.muyi.yifan.jimu.service;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.muyi.common.core.constant.SecurityConstants;
import com.muyi.common.satoken.utils.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.jeecg.modules.jmreport.api.JmReportTokenServiceI;

@RequiredArgsConstructor
public class JmReportTokenServiceImpl implements JmReportTokenServiceI {

	/**
	 * 校验 Token 是否有效，即验证通过
	 * @param token JmReport 前端传递的 token
	 * @return 是否认证通过
	 */
	@Override
	public Boolean verifyToken(String token) {
		if (StrUtil.isBlank(token)) {
			return false;
		}
		// 令牌不存在
		if (StpUtil.getLoginIdByToken(token) == null) {
			return false;
		}
		return true;
	}

	@Override
	public String getUsername(String token) {
		return LoginHelper.getUsername(token);
	}

}

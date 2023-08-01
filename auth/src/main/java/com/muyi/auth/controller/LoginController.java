package com.muyi.auth.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.muyi.admin.api.entity.SysLog;
import com.muyi.auth.form.LoginBody;
import com.muyi.auth.service.LoginService;
import com.muyi.common.core.util.R;
import com.muyi.common.core.util.SpringContextHolder;
import com.muyi.common.log.event.SysLogEvent;
import com.muyi.common.log.util.SysLogUtils;
import com.muyi.common.satoken.utils.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * token 控制
 *
 */
@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;

	/**
	 * 后台登陆
	 */
	@PostMapping("/login")
	public R login(@Validated LoginBody loginBody) {
		return loginService.login(loginBody);
	}

	/**
	 * 手机号登陆
	 *
	 * @author 王晨阳
	 * @version 1.0
	 * @date 2022/8/18 22:19
	 * @desc
	 **/
	@PostMapping("/smsLogin")
	public R smsLogin(String mobile) {
		return loginService.smsLogin(mobile);
	}

	/**
	 * 登出方法
	 */
	@DeleteMapping("logout")
	public R<Void> logout() {
		try {
			String username = LoginHelper.getUsername();
			StpUtil.logout();
			SysLog logVo = SysLogUtils.getSysLog();
			logVo.setTitle(username + "退出登陆");
			// 发送异步日志事件
			logVo.setTime(0L);
			logVo.setCreateBy(username);
			logVo.setUpdateBy(username);
			SpringContextHolder.publishEvent(new SysLogEvent(logVo));
		}
		catch (Exception e) {
		}
		return R.ok();
	}

}

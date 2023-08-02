package com.muyi.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.muyi.admin.api.entity.SysUser;
import com.muyi.admin.api.vo.UserInfoVO;
import com.muyi.admin.service.SysUserService;
import com.muyi.common.core.dto.LoginUser;
import com.muyi.common.core.execption.ErrorCodes;
import com.muyi.common.core.util.MsgUtils;
import com.muyi.common.core.util.R;
import com.muyi.common.satoken.utils.LoginHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 历川
 * @version 1.0
 * @description 用户接口
 * @date 2023/8/2 21:21
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    SysUserService userService;
    /**
     * 获取当前用户所有信息
     * @return 用户信息
     */
    @GetMapping("/info")
    public R<UserInfoVO> getSysUserInfo() {
        String username = LoginHelper.getUsername();
        SysUser user = userService.getOne(Wrappers.<SysUser>query().lambda().eq(SysUser::getUsername, username));
        if (user == null) {
            return R.failed(MsgUtils.getMessage(ErrorCodes.SYS_USER_QUERY_ERROR));
        }
        LoginUser userInfo  = userService.getUserInfo(user);
        UserInfoVO vo = new UserInfoVO();
        vo.setSysUser(user);
        vo.setRoles(userInfo.getRoleIds());
        vo.setPermissions(userInfo.getMenuPermissions());
        return R.ok(vo);
    }
}

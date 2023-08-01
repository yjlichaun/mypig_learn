package com.muyi.admin.api.feign;

import com.muyi.common.core.constant.ServiceNameConstants;
import com.muyi.common.core.dto.LoginUser;
import com.muyi.common.core.util.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @description 远程用户服务
 * @author 历川
 * @date 2023/8/1 14:59
*/
@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.UMPS_SERVICE)
public interface RemoteUserService {
    /**
     * 通过用户名查询用户、角色信息
     * @param username 用户名
     * @return R
     */
    @GetMapping("/user/info/{username}")
    R<LoginUser> info(@PathVariable("username") String username);
    
    /**
     * 通过手机号码查询用户、角色信息
     * @param phone 手机号码
     * @return R
     */
    @GetMapping("/app/info/{phone}")
    R<LoginUser> infoByMobile(@PathVariable("phone") String phone);
    
    /**
     * 根据部门id，查询对应的用户 id 集合
     * @param deptIds 部门id 集合
     * @return 用户 id 集合
     */
    @GetMapping("/user/ids")
    R<List<Long>> listUserIdByDeptIds(@RequestParam("deptIds") Set<Long> deptIds);
    
}

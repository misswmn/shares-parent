package com.shares.biz.shared.shiro.token.manager;

import com.shares.common.service.facade.dto.SysUserParamDTO;
import com.shares.core.model.bo.SysUserBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;

/**
 * @author wangmn
 * @date 2017/8/4
 */
public class TokenManager {

    public static SysUserBO login(SysUserParamDTO user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(), false);
        SecurityUtils.getSubject().login(token);
        return getCurrentUser();
    }

    /**
     * 获取当前登录的用户对象
     */
    public static SysUserBO getCurrentUser() {
        return (SysUserBO) SecurityUtils.getSubject().getPrincipal();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getUserId() {
        return getCurrentUser() == null ? null : getCurrentUser().getUserId();
    }
}

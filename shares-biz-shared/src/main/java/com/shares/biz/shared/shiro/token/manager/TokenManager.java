package com.shares.biz.shared.shiro.token.manager;

import com.shares.biz.shared.shiro.token.ShiroToken;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.core.model.bo.UserBO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * @author wangmn
 * @date 2017/8/4
 */
public class TokenManager {

    public static UserBO login(UserParamDTO user) {
        ShiroToken token = new ShiroToken(user.getUsername(), user.getPassword(), user.isRememberMe());
        SecurityUtils.getSubject().login(token);
        return getCurrentUser();
    }

    /**
     * 获取当前登录的用户对象
     */
    public static UserBO getCurrentUser() {
        return (UserBO) SecurityUtils.getSubject().getPrincipal();
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

    public static Long getUserId() {
        return getCurrentUser() == null ? null : getCurrentUser().getId();
    }
}
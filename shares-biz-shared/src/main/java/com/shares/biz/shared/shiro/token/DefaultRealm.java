package com.shares.biz.shared.shiro.token;

import com.shares.core.model.bo.UserBO;
import com.shares.core.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author wangmn
 * @date 2017/8/5
 */
public class DefaultRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRealm.class);
    @Autowired
    private UserService userService;

    public DefaultRealm() {
        super();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        ShiroToken shiroToken = (ShiroToken) token;
        UserBO user = userService.login(shiroToken.getUsername(), new String(shiroToken.getPassword()));
        if (user == null) {
            throw new AccountException("用户名或密码不正确");
        } else if (user.getStatus() == UserBO.Status.DISABLED) {
            throw new DisabledAccountException("该用户已经被冻结");
        } else {
            user.setLastUpdateTime(new Date());
            userService.updateLastLoginTime(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
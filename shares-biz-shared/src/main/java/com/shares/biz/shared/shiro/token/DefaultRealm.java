package com.shares.biz.shared.shiro.token;

import com.shares.biz.shared.shiro.token.manager.TokenManager;
import com.shares.core.model.bo.SysUserBO;
import com.shares.core.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

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
        String userId = TokenManager.getUserId();
        if (userId == null) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>(userService.getPermissions(userId));
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        ShiroToken shiroToken = (ShiroToken) token;
        SysUserBO sysUserBO = userService.login(shiroToken.getUsername(), new String(shiroToken.getPassword()));
        if (sysUserBO == null) {
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(sysUserBO, sysUserBO.getPassword(), getName());
    }
}

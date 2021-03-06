package com.shares.biz.shared.shiro.token;

import com.shares.core.model.bo.SysUserBO;
import com.shares.core.service.ResourceCoreService;
import com.shares.core.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
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
    @Inject
    private ResourceCoreService resourceCoreService;

    public DefaultRealm() {
        super();
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SysUserBO userBO = (SysUserBO) principals.getPrimaryPrincipal();
        if (userBO == null) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> permissions = new HashSet<>(resourceCoreService.getResourceSet(userBO.getUserId()));
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken shiroToken = (UsernamePasswordToken) token;
        SysUserBO sysUserBO = userService.login(shiroToken.getUsername(), new String(shiroToken.getPassword()));
        if (sysUserBO == null) {
            throw new IncorrectCredentialsException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(sysUserBO, sysUserBO.getPassword(), getName());
    }
}

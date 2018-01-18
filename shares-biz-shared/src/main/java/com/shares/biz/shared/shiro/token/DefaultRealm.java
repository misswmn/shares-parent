package com.shares.biz.shared.shiro.token;

import com.shares.core.model.bo.UserBO;
import com.shares.core.model.enums.UserStatusEnum;
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
        UserBO userBO = userService.login(shiroToken.getUsername(), new String(shiroToken.getPassword()));
        if (userBO == null) {
            throw new AccountException("用户名或密码不正确");
        } else if (UserStatusEnum.DISABLED.getCode().equals(userBO.getStatus())) {
            throw new DisabledAccountException("该用户已经被冻结");
        } else {
            userBO.setUpdateTime(new Date());
            userService.updateLastLoginTime(userBO);
        }
        return new SimpleAuthenticationInfo(userBO, userBO.getPassword(), getName());
    }
}

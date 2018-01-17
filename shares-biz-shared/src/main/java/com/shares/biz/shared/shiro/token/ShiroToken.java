package com.shares.biz.shared.shiro.token;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

/**
 * @author wangmn
 * @date 2017/8/4
 */
public class ShiroToken extends UsernamePasswordToken implements Serializable {
    private static final long serialVersionUID = -52410460203458739L;

    public ShiroToken(String username, String password, boolean rememberMe) {
        super(username, password,rememberMe);
    }
}

package com.shares.biz.shared.shiro.session;

import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * @author ex-wangmengnan
 * @date 2017/8/30
 */
@Component
public class CustomSessionManager {
    public static final String SESSION_STATUS = "orange-online-status";
    
    @Inject
    private ShiroSessionRepository jedisShiroSessionRepository;
}
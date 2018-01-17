package com.shares.biz.shared.shiro.cache;

import com.shares.biz.shared.shiro.session.CustomSessionManager;
import com.shares.biz.shared.shiro.session.SessionStatus;
import com.shares.biz.shared.shiro.session.ShiroSessionRepository;
import com.shares.common.util.JedisClient;
import com.shares.common.util.SerializeUtil;
import com.shares.core.service.exception.ResponseEnum;
import com.shares.core.service.exception.ServiceException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

@Component
public class JedisShiroSessionRepository implements ShiroSessionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisShiroSessionRepository.class);
    private static final String REDIS_SHIRO_SESSION = "orange_session_";
    private static final int SESSION_VAL_TIME_SPAN = 18 * 1000;
    @Inject
    private JedisClient jedisClient;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            throw new ServiceException(ResponseEnum.ILLEGAL_PARAM, "session is null");
        }
        if (session.getAttribute(CustomSessionManager.SESSION_STATUS) == null) {
            SessionStatus sessionStatus = new SessionStatus();
            session.setAttribute(CustomSessionManager.SESSION_STATUS, sessionStatus);
        }
        byte[] key = SerializeUtil.serialize(buildRedisSessionKey(session.getId()));
        byte[] value = SerializeUtil.serialize(session);
        long sessionTimeout = session.getTimeout() / 1000;
        long expireTime = sessionTimeout + SESSION_VAL_TIME_SPAN + (5 * 60);
        jedisClient.set(key, value, (int) expireTime);
    }

    @Override
    public void deleteSession(Serializable sessionId) {

    }

    @Override
    public Session getSession(Serializable sessionId) {
        byte[] key = SerializeUtil.serialize(buildRedisSessionKey(sessionId));
        byte[] sessionBytes = jedisClient.get(key);
        if (sessionBytes == null) return null;
        return SerializeUtil.deserialize(sessionBytes, Session.class);
    }

    @Override
    public Collection<Session> getAllSessions() {
        return null;
    }

    private String buildRedisSessionKey(Serializable id) {
        return REDIS_SHIRO_SESSION + id;
    }
}

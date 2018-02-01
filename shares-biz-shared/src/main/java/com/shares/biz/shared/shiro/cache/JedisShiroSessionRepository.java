package com.shares.biz.shared.shiro.cache;

import com.shares.biz.shared.shiro.session.ShiroSessionRepository;
import com.shares.common.service.facade.enums.ResponseEnum;
import com.shares.core.model.bo.ConstantsBO;
import com.shares.core.service.exception.ServiceException;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Component
public class JedisShiroSessionRepository implements ShiroSessionRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(JedisShiroSessionRepository.class);
    @Inject
    private RedisTemplate<String, Session> redisTemplate;

    @Override
    public void saveSession(Session session) {
        if (session == null || session.getId() == null)
            throw new ServiceException(ResponseEnum.ILLEGAL_PARAM, "session is null");
        String sessionKey = buildRedisSessionKey(session.getId());
        ValueOperations<String, Session> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(sessionKey, session, session.getTimeout(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void deleteSession(Serializable sessionId) {

    }

    @Override
    public Session getSession(Serializable sessionId) {
        String sessionKey = buildRedisSessionKey(sessionId);
        ValueOperations<String, Session> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(sessionKey);
    }

    @Override
    public Collection<Session> getAllSessions() {
        return null;
    }

    @Override
    public void update(Session session) {
        this.saveSession(session);
    }

    private String buildRedisSessionKey(Serializable id) {
        return ConstantsBO.Session.SHARES_SESSION_REDIS + id;
    }
}

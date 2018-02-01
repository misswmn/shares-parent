package com.shares.biz.shared.shiro;

import com.shares.biz.shared.shiro.session.ShiroSessionRepository;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.Collection;

public class CustomShiroSessionDAO extends AbstractSessionDAO {
    @Inject
    private ShiroSessionRepository jedisShiroSessionRepository;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        jedisShiroSessionRepository.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return jedisShiroSessionRepository.getSession(sessionId);
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        jedisShiroSessionRepository.update(session);
    }

    @Override
    public void delete(Session session) {

    }

    @Override
    public Collection<Session> getActiveSessions() {
        return null;
    }
}

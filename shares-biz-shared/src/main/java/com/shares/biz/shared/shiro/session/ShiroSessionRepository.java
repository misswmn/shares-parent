package com.shares.biz.shared.shiro.session;

import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;

public interface ShiroSessionRepository {
    /**
     * 保存session
     *
     * @param session
     */
    void saveSession(Session session);

    /**
     * 删除session
     *
     * @param sessionId
     */
    void deleteSession(Serializable sessionId);

    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    Session getSession(Serializable sessionId);

    /**
     * 获取所有sessoin
     *
     * @return
     */
    Collection<Session> getAllSessions();

    /**
     * 更新session
     * @author ex-wangmengnan
     * @description
     * @param session
     * @date 2018/2/1 17:48
     */
    void update(Session session);
}

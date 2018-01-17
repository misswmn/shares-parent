package com.shares.biz.shared.shiro.session;

import java.io.Serializable;

/**
 * @author ex-wangmengnan
 * @date 2017/8/30
 */
public class SessionStatus implements Serializable {

    private static final long serialVersionUID = 1646421194734780983L;
    //是否踢出 true:有效，false：踢出。
    private Boolean onlineStatus = Boolean.TRUE;

    public Boolean getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Boolean onlineStatus) {
        this.onlineStatus = onlineStatus;
    }
}
package com.shares.core.model.bo;

/**
 * @author wangmn
 * @description
 * @date 2018/1/17 17:33
 */
public interface ConstantsBO {

    interface Session {
        String SHARES_SESSION_REDIS = "shares_session:";
        String SHARES_SESSION_STATUS = "shares_session_status";
        String SHARES_SESSION_LOGIN = "shares_session_login";
        String SHARES_REDIS_CACHE = "shares_redis_cache:";
        /** 菜单SESSION_KEY */
        String SHARES_SESSION_MENU = "shares_session_menu";
        String SHARES_CRUMB_MENU = "shares_crumb_menu";
    }
}

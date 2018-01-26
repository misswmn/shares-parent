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
    }
}

package com.shares.biz.shared;


import com.shares.common.service.facade.dto.UserParamDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
public interface LoginBusiness {
    /**
     * 登录
     *
     * @param request
     * @param user
     * @return url
     */
    String login(HttpServletRequest request, UserParamDTO user);
}
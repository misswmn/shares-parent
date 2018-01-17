package com.shares.biz.shared;

import com.shares.biz.shared.base.Business;
import com.shares.biz.shared.shiro.token.manager.TokenManager;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.core.service.exception.ResponseEnum;
import com.shares.core.service.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
@Business
public class LoginBusinessImpl implements LoginBusiness {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginBusinessImpl.class);

    @Override
    public String login(HttpServletRequest request, UserParamDTO user) {
        try {
            TokenManager.login(user);
            SavedRequest savedRequest = WebUtils.getSavedRequest(request);
            if (savedRequest != null) {
                return savedRequest.getRequestUrl();
            }
            return "main";
        } catch (DisabledAccountException e) {
            throw new ServiceException(ResponseEnum.ACCOUNT_LOCK);
        } catch (AuthenticationException e) {
            throw new ServiceException(ResponseEnum.USERNAME_PWD_ERROR);
        }
    }
}
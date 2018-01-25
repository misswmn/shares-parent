package com.shares.biz.shared;

import com.shares.biz.shared.base.Business;
import com.shares.biz.shared.shiro.token.manager.TokenManager;
import com.shares.common.service.facade.dto.SysUserParamDTO;
import com.shares.common.service.facade.enums.ResponseEnum;
import com.shares.core.service.exception.ServiceException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
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
    public String login(HttpServletRequest request, SysUserParamDTO user) {
        try {
            TokenManager.login(user);
        } catch (IncorrectCredentialsException e){
            throw new ServiceException(ResponseEnum.USERNAME_PWD_ERROR);
        } catch (AuthenticationException e) {
            throw new ServiceException(ResponseEnum.UNKNOWN_EXCEPTION);
        }
        SavedRequest savedRequest = WebUtils.getSavedRequest(request);
        if (savedRequest != null) {
            return savedRequest.getRequestUrl();
        }
        return "main";
    }
}
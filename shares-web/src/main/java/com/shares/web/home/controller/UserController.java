package com.shares.web.home.controller;

import com.shares.biz.shared.LoginBusiness;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.core.service.exception.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by misswmn on 2017/4/8.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Inject
    private LoginBusiness loginBusiness;

    @RequestMapping(value = "/login", method = RequestMethod.POST, name = "登录")
    public ResultBean login(HttpServletRequest request, @Valid UserParamDTO user, BindingResult result) {
        return ResultBean.format(loginBusiness.login(request, user));
    }
}

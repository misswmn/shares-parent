package com.shares.web.home.controller;

import com.shares.biz.shared.LoginBusiness;
import com.shares.biz.shared.PermissionSupportBusiness;
import com.shares.biz.shared.UserBusiness;
import com.shares.common.service.facade.dto.SysUserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.core.service.exception.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by misswmn on 2017/4/8.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Inject
    private LoginBusiness loginBusiness;
    @Inject
    private UserBusiness userBusiness;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultBean login(HttpServletRequest request, SysUserParamDTO user, BindingResult result) {
        return ResultBean.format(loginBusiness.login(request, user));
    }

    @RequestMapping(value = "/list")
    public ResultBean list(PageRequestDTO<SysUserParamDTO> requestDTO, BindingResult result) {
        return ResultBean.format(userBusiness.listUser(requestDTO));
    }

    @RequestMapping(value = "/role")
    public ResultBean listRole(PageRequestDTO<SysUserParamDTO> requestDTO, BindingResult result) {
        return ResultBean.format(userBusiness.listUser(requestDTO));
    }
}

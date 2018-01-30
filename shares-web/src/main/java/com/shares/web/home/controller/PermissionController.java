package com.shares.web.home.controller;

import com.shares.biz.shared.PermissionSupportBusiness;
import com.shares.core.service.exception.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Inject
    private PermissionSupportBusiness permissionSupportBusiness;

    @RequestMapping(value = {"/load"}, method = RequestMethod.GET)
    public ResultBean listPermission() {
        return ResultBean.format(permissionSupportBusiness.getRequestMapping());
    }
}
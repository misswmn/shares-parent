package com.shares.web.home.controller;

import com.shares.core.service.exception.ResultBean;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
@RestController
public class IndexController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @RequiresPermissions(value = "main")
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(ModelMap modelMap) {
        return returnPage(modelMap, "home", null);
    }

    @RequestMapping(value = "/lack/permission", method = RequestMethod.GET)
    public ResultBean userNoPermission() {
        return ResultBean.format("抱歉你没有操作权限");
    }
}
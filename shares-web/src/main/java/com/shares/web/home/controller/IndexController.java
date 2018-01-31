package com.shares.web.home.controller;

import com.shares.core.service.exception.ResultBean;
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    /**
     * 通用页面跳转
     * @author ex-wangmengnan
     * @description
     * @param modelMap
     * @date 2018/1/31 17:45
     */
    @RequestMapping(value = "/page/main", method = RequestMethod.GET)
    public ModelAndView toPage(ModelMap modelMap) {

        return returnPage(modelMap, "home", null);
    }

    @RequestMapping(value = "/lack/permission", method = RequestMethod.GET)
    public ResultBean userNoPermission() {
        return ResultBean.format("抱歉你没有操作权限");
    }
}
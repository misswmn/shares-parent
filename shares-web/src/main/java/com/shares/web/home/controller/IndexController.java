package com.shares.web.home.controller;

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

    @RequestMapping(value = "/", method = RequestMethod.GET, name = "登录页")
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET, name = "跳转到首页")
    public ModelAndView main(ModelMap modelMap) {
        return returnPage(modelMap, "home", null);
    }
}
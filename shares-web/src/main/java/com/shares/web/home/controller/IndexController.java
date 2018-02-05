package com.shares.web.home.controller;

import com.shares.biz.shared.UserBusiness;
import com.shares.biz.shared.shiro.token.manager.TokenManager;
import com.shares.common.util.JsonUtils;
import com.shares.core.service.exception.ResultBean;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
@RestController
public class IndexController extends BaseController {

    @Inject
    private UserBusiness userBusiness;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(ModelMap modelMap) {
        String userId = TokenManager.getUserId();
        modelMap.addAttribute("menuTree", userBusiness.getMenuTree(userId));
        return returnPage(modelMap, "home", null);
    }

    @RequestMapping(value = "/lack/permission", method = RequestMethod.GET)
    public ResultBean userNoPermission() {
        return ResultBean.format("抱歉你没有操作权限");
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public ModelAndView page(@PathVariable String page, ModelMap modelMap) {
        return returnPage(modelMap, page, null);
    }
}
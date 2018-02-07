package com.shares.web.home.controller;

import com.shares.biz.shared.shiro.token.manager.TokenManager;
import com.shares.common.dal.plugin.common.util.StringUtils;
import com.shares.common.service.facade.enums.ResponseEnum;
import com.shares.core.model.bo.ConstantsBO;
import com.shares.core.service.exception.ServiceException;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class BaseController {

    /**
     * 通用页面重定向
     *
     * @param modelMap
     * @param page     页面url
     * @param msg
     * @return
     */
    protected ModelAndView returnPage(ModelMap modelMap, String page, Object data, String msg) {
        if (StringUtils.isBlank(page)) {
            throw new ServiceException(ResponseEnum.ILLEGAL_PARAM, "property 'page' app error");
        }
        modelMap.put("page", page);
        modelMap.put("msg", msg);
        //当前页面路径
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("data", data);
        modelMap.addAttribute("menuTree", TokenManager.getSession()
                .getAttribute(ConstantsBO.Session.SHARES_SESSION_MENU));
        return new ModelAndView("main");
    }
}

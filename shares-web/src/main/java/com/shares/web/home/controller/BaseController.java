package com.shares.web.home.controller;

import com.shares.biz.shared.shiro.token.manager.TokenManager;
import com.shares.common.dal.plugin.common.util.StringUtils;
import com.shares.common.service.facade.dto.SysUserResourceDTO;
import com.shares.common.service.facade.enums.ResponseEnum;
import com.shares.core.model.bo.ConstantsBO;
import com.shares.core.service.exception.ServiceException;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BaseController {

    /**
     * 页面导航菜单树
     */
    private ConcurrentMap<String, List<SysUserResourceDTO>> crumbMap = new ConcurrentHashMap<>();

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
        List<SysUserResourceDTO> menuTree = (List<SysUserResourceDTO>) TokenManager.getSession()
                .getAttribute(ConstantsBO.Session.SHARES_SESSION_MENU);
        modelMap.addAttribute("menuTree", menuTree);
        crumbMap.clear();
        this.getMenuCrumb(data == null ? null : data.toString(), menuTree);
        modelMap.addAttribute("menuCrumb", crumbMap.get(ConstantsBO.Session.SHARES_CRUMB_MENU));
        return new ModelAndView("main");
    }

    /**
     * 获取页面导航菜单树
     *
     * @param menuCode
     * @param menuTree
     * @author ex-wangmengnan
     * @description
     * @date 2018/2/7 14:13
     */
    private void getMenuCrumb(String menuCode, List<SysUserResourceDTO> menuTree) {
        if (CollectionUtils.isEmpty(menuTree)) return;
        for (SysUserResourceDTO menu : menuTree) {
            if (menuCode.startsWith(menu.getMenuCode())) {
                List<SysUserResourceDTO> menuList = crumbMap.get(ConstantsBO.Session.SHARES_CRUMB_MENU);
                if (CollectionUtils.isEmpty(menuList)) {
                    menuList = new ArrayList<>();
                    menuList.add(menu);
                    crumbMap.put(ConstantsBO.Session.SHARES_CRUMB_MENU, menuList);
                } else {
                    menuList.add(menu);
                }
                if (!CollectionUtils.isEmpty(menu.getChildNodes())) {
                    getMenuCrumb(menuCode, menu.getChildNodes());
                }
            }
        }
    }

}

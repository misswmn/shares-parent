package com.shares.biz.shared;


import com.shares.core.model.bo.PermissionBO;

import java.util.List;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
public interface PermissionSupportBusiness {
    /**
     * 获取spring mvc所有@RequestMapping
     */
    List<PermissionBO> getRequestMapping();
}
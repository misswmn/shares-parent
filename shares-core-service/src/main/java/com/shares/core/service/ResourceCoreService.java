package com.shares.core.service;

import java.util.Set;

/**
 * @author wangmn
 * @description
 * @date 2018/2/2 15:50
 */
public interface ResourceCoreService {

    /**
     * 根据用户ID查询菜单权限
     *
     * @param userId
     * @author ex-wangmengnan
     * @description
     * @date 2018/2/2 15:52
     */
    String getMenuTree(String userId);

    Set<String> getResourceSet(String userId);
}

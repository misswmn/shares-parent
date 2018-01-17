package com.shares.core.service.impl;

import com.shares.core.model.bo.RoleBO;
import com.shares.core.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/26
 * @description
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Override
    public void saveOne(RoleBO role) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++");
    }
}

package com.shares.core.service;

import com.shares.core.model.bo.RoleBO;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/26
 * @description
 */
@Validated
public interface RoleService {
    void saveOne(@Valid RoleBO role);
}

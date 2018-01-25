package com.shares.common.dal.daointerface;

import com.shares.common.dal.dataobject.auto.SysUserDO;
import com.shares.common.dal.plugin.common.model.PageRequest;

import java.util.List;

/**
 * @author wangmn
 * @description
 * @date 2018/1/24 15:06
 */
public interface SysUserInfoDOMapper {
    List<SysUserDO> listUserByPage(PageRequest<SysUserDO> request);

    List<String> getPermissions(String userId);
}

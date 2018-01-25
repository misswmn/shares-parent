package com.shares.biz.shared;

import com.shares.common.service.facade.dto.SysUserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.common.service.facade.dto.page.PageResultDTO;
import com.shares.core.service.exception.ServiceException;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:31
 */
public interface UserBusiness {
    /**
     * 用户列表
     *
     * @param
     * @author ex-wangmengnan
     * @description
     * @date 2018/1/19 16:33
     */
    PageResultDTO<SysUserParamDTO> listUser(PageRequestDTO<SysUserParamDTO> requestDTO) throws ServiceException;
}

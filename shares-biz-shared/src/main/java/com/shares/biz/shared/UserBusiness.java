package com.shares.biz.shared;

import com.shares.common.service.facade.dto.UserDTO;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.core.service.exception.ServiceException;

import java.util.List;

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
    List<UserDTO> listUser(PageRequestDTO<UserParamDTO> requestDTO) throws ServiceException;
}
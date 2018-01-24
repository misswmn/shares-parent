package com.shares.biz.shared;

import com.shares.biz.shared.base.Business;
import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.dal.plugin.common.model.PageResult;
import com.shares.common.service.facade.dto.UserDTO;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.common.service.facade.dto.page.PageResultDTO;
import com.shares.core.model.bo.UserBO;
import com.shares.core.model.bo.UserParamBO;
import com.shares.core.service.UserService;
import com.shares.core.service.base.BeanServiceUtil;
import com.shares.core.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import java.util.List;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 16:31
 */
@Business
public class UserBusinessImpl implements UserBusiness {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBusinessImpl.class);
    @Inject
    private UserService userService;

    @Override
    public PageResultDTO<UserDTO> listUser(PageRequestDTO<UserParamDTO> requestDTO) throws ServiceException {
        PageRequest<UserParamBO> pageRequest = new PageRequest<>();
        BeanUtils.copyProperties(requestDTO, pageRequest);
        UserParamBO userParamBO = new UserParamBO();
        if (requestDTO.getParam() != null) {
            BeanUtils.copyProperties(requestDTO.getParam(), userParamBO);
        }
        PageResult<UserBO> resp = userService.listUser(pageRequest);
        PageResultDTO<UserDTO> result = new PageResultDTO<>();
        BeanUtils.copyProperties(resp, result);
        List<UserDTO> userDTOS = BeanServiceUtil.copy(resp.getRows(), UserDTO.class, true);
        result.setRows(userDTOS);
        return result;
    }
}

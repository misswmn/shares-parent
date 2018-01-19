package com.shares.biz.shared;

import com.shares.biz.shared.base.Business;
import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.service.facade.dto.UserDTO;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
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
    public List<UserDTO> listUser(PageRequestDTO<UserParamDTO> requestDTO) throws ServiceException {
        PageRequest<UserParamBO> pageRequest = new PageRequest<>();
        BeanUtils.copyProperties(requestDTO, pageRequest);
        UserParamBO userParamBO = new UserParamBO();
        if (requestDTO.getParam() != null) {
            BeanUtils.copyProperties(requestDTO.getParam(), userParamBO);
        }
        List<UserBO> userBOList = userService.listUser(pageRequest);
        return BeanServiceUtil.copy(userBOList, UserDTO.class, true);
    }
}

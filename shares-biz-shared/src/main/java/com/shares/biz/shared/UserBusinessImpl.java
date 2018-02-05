package com.shares.biz.shared;

import com.google.gson.reflect.TypeToken;
import com.shares.biz.shared.base.Business;
import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.dal.plugin.common.model.PageResult;
import com.shares.common.service.facade.dto.SysUserParamDTO;
import com.shares.common.service.facade.dto.SysUserResourceDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.common.service.facade.dto.page.PageResultDTO;
import com.shares.common.service.facade.enums.ResponseEnum;
import com.shares.common.util.JsonUtils;
import com.shares.core.model.bo.SysUserBO;
import com.shares.core.model.bo.UserParamBO;
import com.shares.core.service.ResourceCoreService;
import com.shares.core.service.UserService;
import com.shares.core.service.base.BeanServiceUtil;
import com.shares.core.service.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import javax.inject.Inject;
import java.lang.reflect.Type;
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
    @Inject
    private ResourceCoreService resourceCoreService;

    @Override
    public PageResultDTO<SysUserParamDTO> listUser(PageRequestDTO<SysUserParamDTO> requestDTO) throws ServiceException {
        PageRequest<UserParamBO> pageRequest = new PageRequest<>();
        BeanUtils.copyProperties(requestDTO, pageRequest);
        if (requestDTO.getParam() != null) {
            UserParamBO userParamBO = new UserParamBO();
            BeanUtils.copyProperties(requestDTO.getParam(), userParamBO);
            pageRequest.setParam(userParamBO);
        }
        PageResult<SysUserBO> resp = userService.listUser(pageRequest);
        PageResultDTO<SysUserParamDTO> result = new PageResultDTO<>();
        BeanUtils.copyProperties(resp, result);
        List<SysUserParamDTO> sysUserParamDTOS = BeanServiceUtil.copy(resp.getRows(), SysUserParamDTO.class, true);
        result.setRows(sysUserParamDTOS);
        return result;
    }

    @Override
    public List<SysUserResourceDTO> getMenuTree(String userId) throws ServiceException {
        if (userId == null) {
            throw new ServiceException(ResponseEnum.AUTH_ERROR, "用户未登陆");
        }
        String resourceStr = resourceCoreService.getMenuTree(userId);
        if (resourceStr == null) {
            return null;
        }
        Type resourceType = new TypeToken<List<SysUserResourceDTO>>() {
        }.getType();
        return (List<SysUserResourceDTO>) JsonUtils.jsonToList(resourceStr, resourceType);
    }
}

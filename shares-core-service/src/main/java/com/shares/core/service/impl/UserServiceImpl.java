package com.shares.core.service.impl;

import com.shares.common.dal.daointerface.UserMapper;
import com.shares.common.dal.daoobject.UserDO;
import com.shares.common.dal.daoobject.UserParamDO;
import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.dal.plugin.common.model.PageResult;
import com.shares.common.dal.plugin.common.repository.PageRepository;
import com.shares.core.model.bo.*;
import com.shares.core.service.UserService;
import com.shares.core.service.base.BeanServiceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/2/7
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Inject
    private PageRepository pageRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(Long.parseLong(id));
    }

    @Override
    public PageResult<UserBO> listUser(PageRequest<UserParamBO> pageRequest) {
        PageRequest<UserParamDO> request = new PageRequest<>();
        BeanUtils.copyProperties(pageRequest, request);
        UserParamDO paramDO = new UserParamDO();
        if (pageRequest.getParam() != null) {
            BeanUtils.copyProperties(pageRequest.getParam(), paramDO);
        }
        PageResult<UserDO> pageResult = pageRepository.selectPaging(UserMapper.class, "listUserByPage", request);
        PageResult<UserBO> result = new PageResult<>();
        BeanUtils.copyProperties(pageRequest, request);
        List<UserBO> userBOS = BeanServiceUtil.copy(pageResult.getRows(), UserBO.class);
        result.setRows(userBOS);
        return result;
    }

    @Override
    public void saveOne(ClientBO clientDTO) {
        System.out.println("+++++++++++++++++++++");
    }

    @Override
    public void saveOne(SimpleBO simple) {
        System.out.println("+++++++++++++++++++++");
    }

    @Override
    public void changePwd(UserPasswordBO user) {
        System.out.println("+++++++++++++++++++++");

    }

    @Override
    public void save(SampleBO sample) {
        System.out.println("+++++++++++++++++++++");
    }

    @Override
    public UserBO login(String username, String password) {
        UserDO userDO = userMapper.login(username, password);
        if (userDO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userDO, userBO);
        return userBO;
    }
}

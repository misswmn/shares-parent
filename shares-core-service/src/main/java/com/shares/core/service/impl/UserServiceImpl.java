package com.shares.core.service.impl;

import com.shares.common.dal.daointerface.UserMapper;
import com.shares.common.dal.daoobject.UserDO;
import com.shares.core.model.bo.*;
import com.shares.core.service.UserService;
import com.shares.core.service.base.BeanServiceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/2/7
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(Long.parseLong(id));
    }

    @Override
    public List<UserBO> findAll() {
        List<UserDO> userDOS = userMapper.findAll();
        return BeanServiceUtil.copy(userDOS, UserBO.class);
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
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userDO, userBO);
        return userBO;
    }

    @Override
    public void updateLastLoginTime(UserBO user) {

    }
}

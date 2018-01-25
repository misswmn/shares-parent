package com.shares.core.service.impl;

import com.shares.common.dal.daointerface.SysUserInfoDOMapper;
import com.shares.common.dal.daointerface.auto.SysUserDOMapper;
import com.shares.common.dal.dataobject.UserParamDO;
import com.shares.common.dal.dataobject.auto.SysUserDO;
import com.shares.common.dal.dataobject.auto.SysUserDOExample;
import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.dal.plugin.common.model.PageResult;
import com.shares.common.dal.plugin.common.repository.PageRepository;
import com.shares.core.model.bo.*;
import com.shares.core.model.enums.IsDeleteEnum;
import com.shares.core.service.UserService;
import com.shares.core.service.base.BeanServiceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Inject
    private SysUserDOMapper sysUserDOMapper;
    @Inject
    private SysUserInfoDOMapper sysUserInfoDOMapper;

    @Override
    public void delete(String id) {
    }

    @Override
    public PageResult<SysUserBO> listUser(PageRequest<UserParamBO> pageRequest) {
        PageRequest<UserParamDO> request = new PageRequest<>();
        BeanUtils.copyProperties(pageRequest, request);
        if (pageRequest.getParam() != null) {
            UserParamDO paramDO = new UserParamDO();
            BeanUtils.copyProperties(pageRequest.getParam(), paramDO);
        }
        PageResult<SysUserDO> pageResult = pageRepository.selectPaging(SysUserInfoDOMapper.class, "listUserByPage", request);
        PageResult<SysUserBO> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult, result);
        List<SysUserBO> sysUserBOS = BeanServiceUtil.copy(pageResult.getRows(), SysUserBO.class);
        result.setRows(sysUserBOS);
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
    public SysUserBO login(String username, String password) {
        SysUserDOExample example = new SysUserDOExample();
        example.createCriteria().andUsernameEqualTo(username)
                .andPasswordEqualTo(password).andIsDeleteEqualTo(IsDeleteEnum.N.getCode());
        List<SysUserDO> sysUserDOList = sysUserDOMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(sysUserDOList)) {
            return null;
        }
        SysUserDO sysUserDO = sysUserDOList.get(0);
        SysUserBO sysUserBO = new SysUserBO();
        BeanUtils.copyProperties(sysUserDO, sysUserBO);
        return sysUserBO;
    }

    @Override
    public List<String> getPermissions(String userId) {
        List<String> permissions = sysUserInfoDOMapper.getPermissions(userId);
        return null;
    }
}

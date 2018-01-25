package com.shares.core.service;

import com.shares.common.dal.plugin.common.model.PageRequest;
import com.shares.common.dal.plugin.common.model.PageResult;
import com.shares.core.model.bo.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/2/7
 * @description
 */
@Validated
public interface UserService {

    void delete(@NotEmpty(message = "{user.service.delete.id}") String id);

    PageResult<SysUserBO> listUser(PageRequest<UserParamBO> pageRequest);

    void saveOne(@Valid ClientBO clientDTO);

    void saveOne(@Valid SimpleBO simple);

    void changePwd(@NotNull(message = "{common.param.illegal}") @Valid UserPasswordBO user);

    void save(@NotNull(message = "{common.param.illegal}") @Valid SampleBO sample);

    SysUserBO login(@NotNull(message = "{common.param.illegal}") String username,
                    @NotNull(message = "{common.param.illegal}") String password);

    /**
     * 获取用户权限
     *
     * @param userId
     * @author ex-wangmengnan
     * @description
     * @date 2018/1/25 16:22
     */
    List<String> getPermissions(String userId);
}

package com.shares.core.service;

import com.shares.common.dal.plugin.common.model.PageRequest;
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

    List<UserBO> listUser(PageRequest<UserParamBO> pageRequest);

    void saveOne(@Valid ClientBO clientDTO);

    void saveOne(@Valid SimpleBO simple);

    void changePwd(@NotNull(message = "{common.param.illegal}") @Valid UserPasswordBO user);

    void save(@NotNull(message = "{common.param.illegal}") @Valid SampleBO sample);

    UserBO login(@NotNull(message = "{common.param.illegal}") String username,
               @NotNull(message = "{common.param.illegal}") String password);
}

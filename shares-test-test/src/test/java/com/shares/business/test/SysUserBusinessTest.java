package com.shares.business.test;

import com.shares.biz.shared.UserBusiness;
import com.shares.common.service.facade.dto.SysUserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.common.service.facade.dto.page.PageResultDTO;
import com.shares.common.util.JsonUtils;
import com.shares.core.model.bo.SysUserBO;
import com.shares.core.service.UserService;
import com.shares.web.home.app.WebAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.inject.Inject;

/**
 * @author wangmn
 * @description
 * @date 2018/1/19 17:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebAppConfig.class})
@WebAppConfiguration
public class SysUserBusinessTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserBusinessTest.class);
    @Inject
    private UserBusiness userBusiness;
    @Inject
    private UserService userService;

    @Test
    public void testListUser() {
        PageRequestDTO<SysUserParamDTO> requestDTO = new PageRequestDTO<>();
        requestDTO.setPage(2);
        requestDTO.setCount(10);
        PageResultDTO<SysUserParamDTO> userDTOS = userBusiness.listUser(requestDTO);
        LOGGER.info("{}", JsonUtils.objectToJson(userDTOS));
    }

    @Test
    public void testLogin() {
        SysUserBO sysUserBO = userService.login("wang","1283012");
        LOGGER.info("{}", JsonUtils.objectToJson(sysUserBO));
    }
}

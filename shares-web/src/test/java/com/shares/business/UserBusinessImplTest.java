package com.shares.business;

import com.shares.biz.shared.UserBusiness;
import com.shares.common.service.facade.dto.UserDTO;
import com.shares.common.service.facade.dto.UserParamDTO;
import com.shares.common.service.facade.dto.page.PageRequestDTO;
import com.shares.common.service.facade.dto.page.PageResultDTO;
import com.shares.common.util.JsonUtils;
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
public class UserBusinessImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserBusinessImplTest.class);
    @Inject
    private UserBusiness userBusiness;

    @Test
    public void testListUser() {
        PageRequestDTO<UserParamDTO> requestDTO = new PageRequestDTO<>();
        requestDTO.setPage(1);
        requestDTO.setCount(10);
        PageResultDTO<UserDTO> userDTOS = userBusiness.listUser(requestDTO);
        LOGGER.info("{}", JsonUtils.objectToJson(userDTOS));
    }
}

package com.shares.biz.shared;

import com.shares.biz.shared.base.Business;
import com.shares.core.model.bo.PermissionBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ex-wangmengnan
 * @date 2017/8/31
 */
@Business
public class PermissionSupportBusinessImpl implements PermissionSupportBusiness, ServletContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionSupportBusinessImpl.class);
    private ServletContext servletContext;

    @Override
    public List<PermissionBO> getRequestMapping() {
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        RequestMappingHandlerMapping handlerMapping = webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();
        return handlerMethods.keySet().stream()
                .map(requestMappingInfo -> {
                    PatternsRequestCondition prc = requestMappingInfo.getPatternsCondition();
                    return PermissionBO.builder()
                            .name(requestMappingInfo.getName())
                            .url(new ArrayList<>(prc.getPatterns()))
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
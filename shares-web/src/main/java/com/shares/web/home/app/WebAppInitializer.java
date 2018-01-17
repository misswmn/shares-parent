package com.shares.web.home.app;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * Created by misswmn on 2016/12/27.
 */
@Order(1)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{WebAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebmvcConfig.class};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{encodingFilter()};
    }

    @Override
    protected String getServletName() {
        return "orange";
    }

    private CharacterEncodingFilter encodingFilter() {
        return new CharacterEncodingFilter("UTF-8", true);
    }

    private DelegatingFilterProxy shiroFilterProxy() {
        return new DelegatingFilterProxy();
    }
}

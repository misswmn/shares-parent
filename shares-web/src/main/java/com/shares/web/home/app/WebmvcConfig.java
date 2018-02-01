package com.shares.web.home.app;

import com.shares.web.home.interceptor.AuthorInterceptor;
import freemarker.template.utility.XmlEscape;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangmn
 * @version 1.0
 * @date 2017/6/16
 * @description
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.shares.web.home.controller")
public class WebmvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
        factoryBean.setProviderClass(HibernateValidator.class);
        factoryBean.setValidationMessageSource(messageSource());
        return factoryBean;
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }

    @Bean
    public FreeMarkerViewResolver freeMarkerViewResolver() {
        FreeMarkerViewResolver viewResolver = new FreeMarkerViewResolver();
        viewResolver.setViewClass(FreeMarkerView.class);
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setContentType("text/html;charset=utf-8");
        viewResolver.setSuffix(".ftl");
        viewResolver.setCache(false);
        viewResolver.setOrder(0);
        return viewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
        configurer.setTemplateLoaderPath("/WEB-INF/ftl/");
        configurer.setDefaultEncoding("UTF-8");
        Map<String, Object> variables = new HashMap<>();
        variables.put("xml_escape", xmlEscape());
        configurer.setFreemarkerVariables(variables);
        Properties settings = new Properties();
        settings.put("template_update_delay", "0");
        settings.put("locale", "zh_CN");
        settings.put("datetime_format", "yyyy-MM-dd HH:mm:ss");
        settings.put("date_format", "yyyy-MM-dd");
        settings.put("number_format", "#.##");
        configurer.setFreemarkerSettings(settings);
        return configurer;
    }

    @Bean
    public XmlEscape xmlEscape() {
        return new XmlEscape();
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("assets/**").addResourceLocations("/assets/");
        registry.addResourceHandler("system/js/**").addResourceLocations("/system/js/");
        registry.addResourceHandler("system/css/**").addResourceLocations("/system/css/");
        registry.addResourceHandler("system/images/**").addResourceLocations("/system/images/");
        registry.addResourceHandler("system/bootstrap/**").addResourceLocations("/system/bootstrap/");
        registry.addResourceHandler("/WEB-INF/ftl/**").addResourceLocations("/WEB-INF/ftl/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/user/login");
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor advisor(@Autowired SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public SimpleMappingExceptionResolver resolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties exceptionMappings = new Properties();
        exceptionMappings.put("org.apache.shiro.authz.UnauthorizedException", "/lack/permission");
        resolver.setExceptionMappings(exceptionMappings);
        return resolver;
    }
}

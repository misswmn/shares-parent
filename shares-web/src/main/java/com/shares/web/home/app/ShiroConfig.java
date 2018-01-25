package com.shares.web.home.app;

import com.shares.biz.shared.shiro.CustomShiroSessionDAO;
import com.shares.biz.shared.shiro.filter.LoginFilter;
import com.shares.biz.shared.shiro.token.DefaultRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author wangmn
 * @date 2017/8/6
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
//        shiroFilter.setLoginUrl("/");
//        shiroFilter.setSuccessUrl("/main");
        shiroFilter.setUnauthorizedUrl("/lack/permission");
        shiroFilter.setFilterChainDefinitionMap(getFilterChainDefinitions());
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("login", loginFilter());
        shiroFilter.setFilters(filterMap);
        return shiroFilter;
    }

    private Map<String, String> getFilterChainDefinitions() {
        Map<String, String> filterChainMap = new HashMap<>();
        filterChainMap.put("/", "anon");
        filterChainMap.put("/user/login", "anon");
        filterChainMap.put("/**", "authc");
        return filterChainMap;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(defaultRealm());
        securityManager.setSessionManager(sessionManager());
//        securityManager.setRememberMeManager(rememberMeManager());
//        securityManager.setCacheManager(null);
        return securityManager;
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationInterval(180_000_0);
        sessionManager.setGlobalSessionTimeout(180_000_0);
        sessionManager.setSessionDAO(customShiroSessionDAO());
//        sessionManager.setSessionListeners(Arrays.asList(customSessionListener()));
//        sessionManager.setSessionValidationScheduler(serviceSessionValidationScheduler());
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(cookie());
        return sessionManager;
    }

    @Bean
    public SimpleCookie cookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setHttpOnly(true);
        cookie.setMaxAge(-1);
        return cookie;
    }

    /*@Bean
    public ExecutorServiceSessionValidationScheduler serviceSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        sessionValidationScheduler.setInterval(interVal);
        sessionValidationScheduler.setSessionManager(sessionManager());
        return sessionValidationScheduler;
    }*/

    /*@Bean
    public CustomSessionListener customSessionListener() {
        return new CustomSessionListener();
    }*/

    @Bean
    public CustomShiroSessionDAO customShiroSessionDAO() {
        CustomShiroSessionDAO customShiroSessionDAO = new CustomShiroSessionDAO();
        customShiroSessionDAO.setSessionIdGenerator(sessionIdGenerator());
        return customShiroSessionDAO;
    }

    @Bean
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    /*@Bean
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("fuck"));
        rememberMeManager.setCookie(rememberMeCookie());
        return rememberMeManager;
    }*/

    /**
     * 用户信息记住我功能的相关配置
     */
    /*@Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie cookie = new SimpleCookie("v_v-re-baidu");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 3600);
        return cookie;
    }*/

    @Bean
    public Realm defaultRealm() {
        return new DefaultRealm();
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public MethodInvokingFactoryBean securityUtils() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{securityManager()});
        return methodInvokingFactoryBean;
    }

    /** support shiro annotation */
    @Bean
    public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor advisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
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

package com.shares.web.home.app;

import com.shares.biz.shared.shiro.CustomShiroSessionDAO;
import com.shares.biz.shared.shiro.cache.CustomShiroCacheManager;
import com.shares.biz.shared.shiro.filter.LoginFilter;
import com.shares.biz.shared.shiro.token.DefaultRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author wangmn
 * @description
 * @date 2018/1/30 14:31
 */
@Configuration
public class ShiroConfig {

    @Value("${cookie.name}")
    private String cookieName;
    @Value("${cookie.domain}")
    private String cookieDomain;
    @Value("${cookie.path}")
    private String cookiePath;
    @Value("${cookie.httpOnly}")
    private boolean cookieHttpOnly;
    @Value("${cookie.secure}")
    private boolean cookieSecure;
    @Value("${cookie.maxage}")
    private int cookieMaxAge;

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager());
        shiroFilter.setLoginUrl("/login");
        shiroFilter.setSuccessUrl("/main");
        shiroFilter.setUnauthorizedUrl("/lack/permission");
        shiroFilter.setFilterChainDefinitionMap(getFilterChainDefinitions());
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("login", loginFilter());
        shiroFilter.setFilters(filterMap);
        return shiroFilter;
    }

    private Map<String, String> getFilterChainDefinitions() {
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/login", "anon");
        filterChainMap.put("/assets/**", "anon");
        filterChainMap.put("/system/**", "anon");
        filterChainMap.put("/user/login", "anon");
        filterChainMap.put("/**", "authc");
        return filterChainMap;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(defaultRealm());
        securityManager.setSessionManager(sessionManager());
//        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    @Bean
    public CustomShiroCacheManager cacheManager() {
        return new CustomShiroCacheManager();
    }

    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationInterval(180_000_0);
        sessionManager.setGlobalSessionTimeout(180_000_0);
        sessionManager.setSessionDAO(customShiroSessionDAO());
//        sessionManager.setSessionListeners(Arrays.asList(customSessionListener()));
//        sessionManager.setSessionValidationScheduler(serviceSessionValidationScheduler());
        sessionManager.setSessionValidationSchedulerEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionIdCookie(cookie());
        return sessionManager;
    }

    @Bean
    public SimpleCookie cookie() {
        SimpleCookie cookie = new SimpleCookie();
        cookie.setName(cookieName);
        cookie.setDomain(cookieDomain);
        cookie.setPath(cookiePath);
        cookie.setHttpOnly(cookieHttpOnly);
        cookie.setSecure(cookieSecure);
        cookie.setMaxAge(cookieMaxAge);
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
//
//    @Bean
//    public CookieRememberMeManager rememberMeManager() {
//        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
//        rememberMeManager.setCipherKey(Base64.decode("fuck"));
//        rememberMeManager.setCookie(rememberMeCookie());
//        return rememberMeManager;
//    }
//
//    /**
//     * 用户信息记住我功能的相关配置
//     */
//    @Bean
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie cookie = new SimpleCookie("v_v-re-baidu");
//        cookie.setHttpOnly(true);
//        cookie.setMaxAge(30 * 24 * 3600);
//        return cookie;
//    }
//
    @Bean
    public Realm defaultRealm() {
        return new DefaultRealm();
    }

    @Bean
    public LoginFilter loginFilter() {
        return new LoginFilter();
    }

    @Bean
    public MethodInvokingFactoryBean setSecurityManagerFactoryBean() {
        MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
        methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        methodInvokingFactoryBean.setArguments(new Object[]{securityManager()});
        return methodInvokingFactoryBean;
    }

    /** support shiro annotation */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator autoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

}

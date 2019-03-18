package com.sanshao.shiro.shirodemo;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@Configuration
public class SimpleShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(SimpleShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager) {
        logger.info("ShiroConfiguration simpleconfig start");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器
        Map<String, Filter> filterMap = new HashMap<String, Filter>();
        SimpleTokenFilter tokenFilter = new SimpleTokenFilter();
        filterMap.put("tokenFilter", tokenFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器.必須使用LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/**", "tokenFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置realm
        securityManager.setRealm(simpleShiroRealm());

        //注入緩存管理器 如果不加入此注入，會選擇默認的配置
        securityManager.setCacheManager(ehCacheManager());

        //注入Session管理器
        securityManager.setSessionManager(defaultWebSessionManager());
        return securityManager;
    }


    @Bean
    public SimpleShiroRealm simpleShiroRealm() {
        SimpleShiroRealm simpleShiroRealm = new SimpleShiroRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(10);
        simpleShiroRealm.setCredentialsMatcher(matcher);
        return simpleShiroRealm;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }


    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehcache = new EhCacheManager();
        ehcache.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehcache;
    }

    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
        defaultWebSessionManager.setGlobalSessionTimeout(30*60*1000);
        defaultWebSessionManager.setSessionDAO(enterpriseCacheSessionDAO());
        return defaultWebSessionManager;
    }

    //初始化SessionDao。使用ehcache
    @Bean
    public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO() {
        EnterpriseCacheSessionDAO enterpriseCacheSessionDAO = new EnterpriseCacheSessionDAO();
        //添加Ehcache活跃缓存名称(必须和Ehcache缓存名称一致)
        enterpriseCacheSessionDAO.setActiveSessionsCacheName("shiro-activeSessionCache");
        return enterpriseCacheSessionDAO;
    }


}

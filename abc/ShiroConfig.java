package com.mars.ums.gateway.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

//@Configuration
public class ShiroConfig {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean
    public ShiroFilterFactoryBean shirFilter(org.apache.shiro.mgt.SecurityManager securityManager) {
        logger.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //注入securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //自定义拦截器
        Map<String, Filter> filterMap = new HashMap<String, Filter>();
        TokenFilter tokenFilter = new TokenFilter();
        filterMap.put("tokenFilter", tokenFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        //拦截器.必須使用LinkedHashMap
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
//        filterChainDefinitionMap.put("/**", "anon");
        filterChainDefinitionMap.put("/**", "tokenFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public org.apache.shiro.mgt.SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //配置realm
        securityManager.setRealm(myShiroRealm());
        //注入緩存管理器 如果不加入此注入，會選擇默認的配置
        securityManager.setCacheManager(ehCacheManager());

        //注入Session管理器
        securityManager.setSessionManager(defaultWebSessionManager());

        return securityManager;
    }


    @Bean
    public MyShiroRealm myShiroRealm() {
        MyShiroRealm myShiroRealm = new MyShiroRealm();
//        RetryLimitHashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher(ehCacheManager());
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("MD5");
        matcher.setHashIterations(10);
        myShiroRealm.setCredentialsMatcher(matcher);
        return myShiroRealm;
    }

    //初始化WebSession管理器
    @Bean
    public DefaultWebSessionManager defaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionIdUrlRewritingEnabled(false);
//        Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//        listeners.add(new ShiroSessionListener());
//
//        defaultWebSessionManager.setSessionListeners(listeners);
//        defaultWebSessionManager.setGlobalSessionTimeout(10*60*1000);
//        defaultWebSessionManager.setDeleteInvalidSessions(true);
//        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
//        defaultWebSessionManager.setSessionValidationInterval(5000);

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

    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehcache = new EhCacheManager();
        ehcache.setCacheManagerConfigFile("classpath:ehcache.xml");
        return ehcache;
    }



}

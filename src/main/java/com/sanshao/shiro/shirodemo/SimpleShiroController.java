package com.sanshao.shiro.shirodemo;


import com.sanshao.shiro.bean.auth.CurrentUser;
import com.sanshao.shiro.bean.auth.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@RestController
public class SimpleShiroController {

    @Autowired
    EnterpriseCacheSessionDAO enterpriseCacheSessionDAO;

    @Autowired
    SimpleShiroRealm simpleShiroRealm;

    @Autowired
    DefaultWebSessionManager defaultWebSessionManager;


    /**
     * 登录功能。登录时将其他同名用户踢下线。
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(), user.getPassword());

        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            return "failed";
        }


        Session thisSession = subject.getSession();
        Collection<Session> sessions = enterpriseCacheSessionDAO.getActiveSessions();
        for(Session session:sessions){
            if(thisSession.getId().equals(session.getId())){
                continue;
            }
            String host = session.getHost();
            String user0 = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString();
            Date lastAccessTime = session.getLastAccessTime();
            if(user.getUsername().equals(user0)){
                session.setTimeout(0);
                break;
            }
        }


        return "success";
    }


    /**
     * 测试方法。测试permission注解
     * @return
     */
    @RequestMapping("/aaa")
    @RequiresPermissions("Add Node")
    public String testAAA() {
        /*
        Subject subject = SecurityUtils.getSubject();
        System.out.println("登陆的用户名是=" + subject.getPrincipal().toString());
        boolean flag = subject.isPermitted("Config Node");
        if (flag) {
            System.out.println("我拥有此权限===" + flag);
            return "I can fly";
        }else{
            return "nonono";
        }
        */


        Subject subject = SecurityUtils.getSubject();
        Session session =subject.getSession();
        System.out.println(session.getId());
        Iterator it = session.getAttributeKeys().iterator();
        while (it.hasNext()){
            String key = it.next().toString();
            System.out.println(key+"=="+session.getAttribute(key));
        }

        return "123";
    }


    /**
     * 获取所有的session中的用户信息
     */
    @GetMapping("/currentusers")
    public ArrayList<CurrentUser> getCurrentUsers(){
        Collection<Session> sessions = enterpriseCacheSessionDAO.getActiveSessions();
        ArrayList<CurrentUser> list = new ArrayList<CurrentUser>();
        for(Session session:sessions){
            CurrentUser currentUser = new CurrentUser();
            currentUser.setHost(session.getHost());
            currentUser.setUsername(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString());
            currentUser.setLastAccessTime(session.getLastAccessTime());
            currentUser.setStartTime(session.getStartTimestamp());
            list.add(currentUser);
        }
        return list;

    }


    /**
     * 清空当前缓存。授权缓存
     */
    @GetMapping("/clearcache")
    public void clearCache(){
        simpleShiroRealm.clearCached();
    }


    /**
     * 踢出用户
     * @param
     */
    @GetMapping("/knockout")
    public String knockout(@RequestParam(value="username") String username){
        Collection<Session> sessions = enterpriseCacheSessionDAO.getActiveSessions();

        for(Session session:sessions){
            String user0 = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY).toString();
            if(username.equals(user0)){
                session.setTimeout(0);
                enterpriseCacheSessionDAO.delete(session);
                break;
            }
        }
        return "knockout";

    }


}

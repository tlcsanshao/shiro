package com.mars.ums.gateway.shiro;

import com.mars.ums.jpa.bean.auth.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RestController
public class ShiroController {

    @Autowired
    EnterpriseCacheSessionDAO enterpriseCacheSessionDAO;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(), user.getPassword());

        //放入session
//        session.setAttribute(session.getId(), user);
//        Iterator<Object> keyItr = session.getAttributeKeys().iterator();
//        while (keyItr.hasNext()) {
//            String k = (String) keyItr.next();
//            System.out.println(k + "===" + session.getAttribute(k));
//        }

        try {
            subject.login(usernamePasswordToken);
            session.setAttribute(session.getId(),user);
        } catch (Exception e) {
            session.stop();
            return "failed";
        }


        return "success";
    }

    @RequestMapping("/aaa")
    public String testAAA() {
        return "aaa";
    }


    //放入session
//        Session session = subject.getSession();
//        System.out.println(session.getHost());
//        System.out.println(session.getId());
//        System.out.println(session.getStartTimestamp());
//        System.out.println(session.getLastAccessTime());
//        session.touch();
//        session.setAttribute(session.getId(), user);
//        Iterator<Object> keyItr = session.getAttributeKeys().iterator();
//        while (keyItr.hasNext()) {
//            String k = (String) keyItr.next();
//            System.out.println(k + "===" + session.getAttribute(k));
//        }

}

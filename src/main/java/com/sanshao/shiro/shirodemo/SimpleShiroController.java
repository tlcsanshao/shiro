package com.sanshao.shiro.shirodemo;


import com.sanshao.shiro.bean.auth.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleShiroController {

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("is login=" + subject.isAuthenticated());
        Session session = subject.getSession();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUsername(), user.getPassword());

        try {
            subject.login(usernamePasswordToken);
            session.setAttribute(session.getId(), user);
        } catch (Exception e) {
            session.stop();
            return "failed";
        }


        return "success";
    }


    @RequestMapping("/aaa")
    @RequiresPermissions("Config Node")
    public String testAAA() {
//        Subject subject = SecurityUtils.getSubject();
//        System.out.println("登陆的用户名是=" + subject.getPrincipal().toString());
//        boolean flag = subject.isPermitted("Config Node");
//        if (flag) {
//            System.out.println("我拥有此权限===" + flag);
//            return "I can fly";
//        }else{
//            return "nonono";
//        }
        return "123";
    }


}

package com.mars.ums.gateway.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TokenFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String url = getPathWithinApplication(request);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        //获取访问的url
        String url = getPathWithinApplication(request);
        System.out.println("url=" + url);
        System.out.println("getSessionId=" + subject.getSession().getId());
//        String username = (String) subject.getPrincipal();
//        session過期后，subject.isAuthenticated() == false

        return true;
//        if (subject.isAuthenticated()) {
//            return true;
//        } else {
//            //去登陆页面
//            System.out.println("我們去登陸了");
//            WebUtils.issueRedirect(request, response, "/index.html");
//
//        }
//
//        return false;
    }
}

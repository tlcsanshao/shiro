package com.sanshao.shiro.shirodemo;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SimpleTokenFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        //获取访问的url
        String url = getPathWithinApplication(request);
        System.out.println("simple url=" + url);
        if(subject.getPrincipal() == null){
            System.out.println("you need to login first");

//          response.getWriter().append("you need to login first");

          //转发重定向
            WebUtils.issueRedirect(request, response, "/");
            return false;
        }

        System.out.println("simple username="+subject.getPrincipal().toString());
        System.out.println("getSessionId=" + subject.getSession().getId());

        return true;

    }
}

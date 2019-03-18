package com.mars.ums.gateway.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

import java.util.concurrent.atomic.AtomicInteger;

public class ShiroSessionListener implements SessionListener {

    private final AtomicInteger sessionCount = new AtomicInteger(0);

    @Override
    public void onStart(Session session) {
        System.out.println("Add a Session");
        sessionCount.incrementAndGet();
    }

    @Override
    public void onStop(Session session) {
        System.out.println("delete a Session");
        sessionCount.decrementAndGet();
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("guoqi a Session");
        sessionCount.decrementAndGet();
    }
}


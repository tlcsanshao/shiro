package com.sanshao.shiro.bean.auth;

import java.util.Date;

public class CurrentUser {

    private String username;
    private String host;
    private Date lastAccessTime;
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", lastAccessTime=" + lastAccessTime +
                '}';
    }
}

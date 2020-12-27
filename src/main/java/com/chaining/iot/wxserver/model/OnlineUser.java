package com.chaining.iot.wxserver.model;

import javax.websocket.Session;

/**
 * 小程序在线用户
 *
 * @program: wxstcgateway
 * @ClassName OnlieUser
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-12 22:22
 * @Version 1.0
 **/
public class OnlineUser {
    /**
     * 使用设备id登录
     */
    private String userId;

    private String nickname;

    private Session session;

    public OnlineUser() {
    }

    public OnlineUser(String userId, String nickname, Session session) {
        this.userId = userId;
        this.nickname = nickname;
        this.session = session;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

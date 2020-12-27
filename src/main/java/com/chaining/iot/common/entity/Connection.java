package com.chaining.iot.common.entity;
import io.netty.channel.Channel;

/**
 * 连接model
 *
 * @program: wxstcgateway
 * @ClassName Connection
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-11 21:13
 * @Version 1.0
 **/
public class Connection {

    private Channel channel;

    private String identityId;

    public Connection(Channel channel, String identityId) {
        this.channel = channel;
        this.identityId = identityId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }
}

package com.chaining.iot.nettyserver.message;


import com.chaining.iot.common.constans.EnumMessageType;

/**
 * @program: wxstciot
 * @ClassName BaseMessageIn
 * @description:
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-06 15:36
 * @Version 1.0
 **/
public abstract class BaseMessageIn implements IMessageIn {
    /**
     * 报文类型
     */
    private EnumMessageType messageType;

    @Override
    public EnumMessageType getMessageType() {
        return messageType;
    }

    @Override
    public void setMessageType(EnumMessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public abstract IMessageIn parseMessage(byte[] userData);
}

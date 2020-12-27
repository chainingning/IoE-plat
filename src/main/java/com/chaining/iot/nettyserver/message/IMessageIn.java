package com.chaining.iot.nettyserver.message;


import com.chaining.iot.common.constans.EnumMessageType;

public interface IMessageIn {

    IMessageIn parseMessage(byte[] data);

    EnumMessageType getMessageType();

    void setMessageType(EnumMessageType messageType);
}

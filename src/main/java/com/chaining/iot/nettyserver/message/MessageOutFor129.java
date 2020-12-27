package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.constans.EnumMessageType;

/**
 * 注册成功
 *
 * @program: wxstcgateway
 * @ClassName MessageOutFor81
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-21 20:13
 * @Version 1.0
 **/
public class MessageOutFor129 extends BaseMessageOut {
    public MessageOutFor129(){
        setMessageType(EnumMessageType.COMMAND129);
    }
    @Override
    public byte[] getUserData() {
        return new byte[0];
    }
}

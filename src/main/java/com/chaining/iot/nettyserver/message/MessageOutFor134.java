package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.constans.EnumMessageType;

/**
 * 0x86 接收到DTU数据
 * @program: wxstcgateway
 * @ClassName MessageOutFor134
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-21 20:13
 * @Version 1.0
 **/
public class MessageOutFor134 extends BaseMessageOut {
    public MessageOutFor134(){
        setMessageType(EnumMessageType.COMMAND134);
    }
    @Override
    public byte[] getUserData() {
        return new byte[0];
    }
}

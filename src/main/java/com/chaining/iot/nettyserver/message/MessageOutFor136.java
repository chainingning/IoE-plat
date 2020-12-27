package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.constans.EnumMessageType;
import com.chaining.iot.common.constans.RelayNum;

/**
 * 控制继电器
 * 0x87
 * @program: wxstcgateway
 * @ClassName MessageOutFor81
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-21 20:13
 * @Version 1.0
 **/
public class MessageOutFor136 extends BaseMessageOut {

    private int relayNum;

    /**
     * 停:2  开:1  关:0
     */
    private RelayNum.ActionEnum action;

    public MessageOutFor136(int relayNum) {
        setMessageType(EnumMessageType.COMMAND136);
        this.relayNum = relayNum;
    }

    @Override
    public byte[] getUserData() {
        byte[] userData = new byte[1];
        userData[0] = (byte)relayNum;
        return userData;
    }


}

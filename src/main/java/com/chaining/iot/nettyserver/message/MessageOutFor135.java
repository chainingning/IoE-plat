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
public class MessageOutFor135 extends BaseMessageOut {

    private RelayNum.RelayNumEnum relayNum;

    /**
     * 停:2  开:1  关:0
     */
    private RelayNum.ActionEnum action;

    public MessageOutFor135(RelayNum.RelayNumEnum relayNum, RelayNum.ActionEnum action) {
        setMessageType(EnumMessageType.COMMAND135);
        this.relayNum = relayNum;
        this.action = action;
    }

    @Override
    public byte[] getUserData() {
        byte[] userData = new byte[2];
        userData[0] = (byte)relayNum.getValue();
        userData[1] = (byte)action.getValue();
        return userData;
    }


}

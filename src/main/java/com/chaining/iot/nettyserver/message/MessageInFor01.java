package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.utils.ByteTools;

/**
 * DTU请求注册报文
 *
 * @program: wxstcgateway
 * @ClassName MessageFor01
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-12 23:05
 * @Version 1.0
 **/
public class MessageInFor01 extends BaseMessageIn {
    /**
     * 身份id
     */
    private String unitId;

    private byte[] userData;

    @Override
    public IMessageIn parseMessage(byte[] userData) {
        MessageInFor01 in = new MessageInFor01();
        String unitId = ByteTools.byteToHex2(userData);
        in.setUnitId(unitId);
        return in;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public byte[] getUserData() {
        return userData;
    }

    public void setUserData(byte[] userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "MessageFor01{" +
                "unitId='" + unitId + '\'' +
                '}';
    }
}

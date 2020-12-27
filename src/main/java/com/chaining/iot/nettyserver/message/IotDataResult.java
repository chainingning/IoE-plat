package com.chaining.iot.nettyserver.message;

import com.chaining.iot.common.constans.EnumMessageType;

/**
 * 持续上报数据
 *
 * @program: wxstcgateway
 * @ClassName IotDataResult
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-17 23:43
 * @Version 1.0
 **/
public class IotDataResult {
    /**
     * 消息类型
     */
    private EnumMessageType messageType;

    /**
     * 设备Id
     */
    private String unitId;

    /**
     *终端数据
     */
    private Object data;

    public EnumMessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(EnumMessageType messageType) {
        this.messageType = messageType;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

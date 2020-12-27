package com.chaining.iot.nettyserver.process;

import com.chaining.iot.common.utils.BeanContext;
import com.chaining.iot.nettyserver.message.BaseMessageIn;
import com.chaining.iot.nettyserver.message.IotDataResult;
import com.chaining.iot.wxserver.websocket.IotSocketServer;
import com.chaining.iot.wxserver.websocket.WxSocketMessage;

/**
 * 发送持续上报数据给小程序的任务
 *
 * @program: wxstcgateway
 * @ClassName IotDataSendProcessTask
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-17 23:41
 * @Version 1.0
 **/
public class IotDataSendProcessTask implements Runnable {
    private BaseMessageIn message;
    private String unitId;

    public IotDataSendProcessTask(String unitId, BaseMessageIn message) {
        this.unitId = unitId;
        this.message = message;
    }

    @Override
    public void run() {
        IotDataResult result = new IotDataResult();
        result.setMessageType(message.getMessageType());
        result.setUnitId(unitId);
        result.setData(message);
        //拿到在线的用户并发送数据
        IotSocketServer.onlineUserMap.values().forEach(user -> {
            WxSocketMessage.singleSend(unitId, message, user);
        });
    }
}

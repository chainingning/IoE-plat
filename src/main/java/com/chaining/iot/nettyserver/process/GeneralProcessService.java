package com.chaining.iot.nettyserver.process;

import com.chaining.iot.nettyserver.message.IMessageIn;
import com.chaining.iot.nettyserver.message.IMessageOut;

/**
 * 通用数据处理
 *
 * @program: wxstcgateway
 * @ClassName GeneralProcessService
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-17 23:23
 * @Version 1.0
 **/
public class GeneralProcessService extends BaseProcessServices{
    @Override
    public IMessageOut processSingle(String unitId, IMessageIn in) {
        return null;
    }
}

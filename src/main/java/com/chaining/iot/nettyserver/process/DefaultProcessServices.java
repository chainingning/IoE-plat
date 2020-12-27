package com.chaining.iot.nettyserver.process;

import com.chaining.iot.nettyserver.message.IMessageIn;
import com.chaining.iot.nettyserver.message.IMessageOut;
import com.chaining.iot.nettyserver.message.MessageOutFor134;

/**
 * 默认处理类
 *
 * @program: wxstcgateway
 * @ClassName DefaultProcessServices
 * @author: ning.chai@foxmail.com
 * @create: 2020-05-22 21:40
 * @Version 1.0
 **/
public class DefaultProcessServices extends BaseProcessServices {
    @Override
    public IMessageOut processSingle(String unitId, IMessageIn in) {
        return null;
    }
}

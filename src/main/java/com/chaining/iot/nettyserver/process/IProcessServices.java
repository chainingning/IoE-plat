package com.chaining.iot.nettyserver.process;

import com.chaining.iot.nettyserver.message.IMessageIn;
import com.chaining.iot.nettyserver.message.IMessageOut;

/**
 * 对接收的报文进行处理
 * @author ningc
 */
public interface IProcessServices {
    /**
     * 处理业务逻辑类
     * @param unitId
     * @param in
     * @return
     */
    public IMessageOut process(String unitId, IMessageIn in);
}

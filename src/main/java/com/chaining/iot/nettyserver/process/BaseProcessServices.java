package com.chaining.iot.nettyserver.process;

import com.chaining.iot.nettyserver.message.IMessageIn;
import com.chaining.iot.nettyserver.message.IMessageOut;
import com.chaining.iot.nettyserver.message.MessageInFor06;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认处理
 *
 * @program: wxstcgateway
 * @ClassName BaseProcessServices
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-10 23:30
 * @Version 1.0
 **/
@Slf4j
public abstract class BaseProcessServices implements IProcessServices {

    @Override
    public IMessageOut process(String unitId, IMessageIn in) {
        log.info("Parse and Send Message:{}",in);
        //处理持续上报数据
        if (in instanceof MessageInFor06) {
            IotDataSendProcessTask task = new IotDataSendProcessTask(unitId,(MessageInFor06)in);
            IotDataSendExectorManager.getInstance().execute(task);
        }
        IMessageOut out = processSingle(unitId, in);
        return out;
    }

    /**
     * 单例处理消息
     * @param unitId
     * @param in
     * @return
     */
    public abstract IMessageOut processSingle(String unitId,IMessageIn in);
}

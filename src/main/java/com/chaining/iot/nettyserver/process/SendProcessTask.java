package com.chaining.iot.nettyserver.process;

import com.chaining.iot.nettyserver.message.IMessageOut;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * 发送数据任务
 *
 * @program: wxstcgateway
 * @ClassName SendProcessTask
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-11 23:25
 * @Version 1.0
 **/
@Slf4j
public class SendProcessTask implements Runnable{
    private IMessageOut message;
    private Channel channel;

    public SendProcessTask(Channel channel,IMessageOut message) {
        this.message = message;
        this.channel = channel;
    }

    @Override
    public void run() {
        //锁住channel
        synchronized (channel){
            if (!channel.isActive()) {
                log.warn("channel :{} disconnect",channel);
                return;
            }
            IMessageOut mo = (IMessageOut)message;
            //发送byte消息
            channel.writeAndFlush(mo);
        }
    }
}

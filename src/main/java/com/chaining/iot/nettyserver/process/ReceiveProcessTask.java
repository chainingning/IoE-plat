package com.chaining.iot.nettyserver.process;
import com.chaining.iot.common.constans.EnumMessageType;
import com.chaining.iot.common.entity.Connection;
import com.chaining.iot.nettyserver.handler.ConnectionHandler;
import com.chaining.iot.nettyserver.message.*;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

import static com.chaining.iot.common.constans.IOTConst.NETTY_CHANNEL_KEY;

/**
 * 处理接收到的终端数据
 *
 * @program: wxstcgateway
 * @ClassName ReceiveProcessTask
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-11 23:49
 * @Version 1.0
 **/
@Slf4j
public class ReceiveProcessTask implements Runnable {

    private IMessageIn message;

    private Channel channel;

    public ReceiveProcessTask(Channel channel,Object message) {
        this.message = (IMessageIn)message;
        this.channel = channel;
    }

    @Override
    public void run() {
//        IMessageIn mi = (IMessageIn)message;
        EnumMessageType messageType = message.getMessageType();
        IMessageOut out = null;

        //如果是握手包，则记录设备ID，下发注册成功
        if (EnumMessageType.COMMAND1 == messageType) {
            String unitId = ((MessageInFor01) message).getUnitId();
            channel.attr(NETTY_CHANNEL_KEY).set(new Connection(channel, unitId));
            ConnectionHandler.addConnection(channel.attr(NETTY_CHANNEL_KEY).get());
            //下发注册成功
            out = new MessageOutFor129();
            log.info("bind unitId:{} to channel:{}",unitId,channel);
        }else {
            //其他数据发送给在线用户
            Connection connection = channel.attr(NETTY_CHANNEL_KEY).get();
            if (null != connection) {
                IProcessServices process = ProcessServicesFactory.getInstance().getProcess(connection.getIdentityId(), messageType);
                out = process.process(connection.getIdentityId(), message);
            }
            //如果connect总是为空，可能一直没发握手包，那么下发重新发送握手包指令
        }
//        Optional.of(out).ifPresent((q)->channel.write(q));
        if (null != out) {
            channel.write(out);
            channel.flush();
        }
        //log.info("服务器收到消息: {}", unitId);
    }

}

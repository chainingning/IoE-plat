package com.chaining.iot.nettyserver.handler;
import com.chaining.iot.common.constans.IOTConst;
import com.chaining.iot.common.entity.Connection;
import com.chaining.iot.nettyserver.process.ReceiveExecutorManager;
import com.chaining.iot.nettyserver.process.ReceiveProcessTask;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * netty服务器处理器
 *
 * @program: wxstcgateway
 * @ClassName NettyServerHandler
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-10 23:00
 * @Version 1.0
 **/
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

   private int readIdleTimes = 0;
    //netty AttributeKey 相对于 web session【重要】
//    public static final AttributeKey<DeviceSession> KEY = AttributeKey.valueOf("IO");
    /**
     * 客户端注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        log.info("{} client registered...", ctx.channel());
    }

    /**
     * 客户端连接时触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("{} connect open",ctx.channel());
    }

    /**
     * 客户端发消息时触发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ReceiveProcessTask task = new ReceiveProcessTask(ctx.channel(), msg);
        ReceiveExecutorManager.getInstance().execute(task);
//        ctx.channel().attr(NETTY_CHANNEL_KEY).set(new Connection(ctx.channel(), msg.toString()));
//        ConnectionHandler.addConnection(ctx.channel().attr(NETTY_CHANNEL_KEY).get());
//        log.info("服务器收到消息: {}", msg.toString());
//        ctx.write("hello\r\n");
//        ctx.flush();
    }

    /**
     * channel的写状态变化的时候触发
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        log.info("我发消息了");
    }

    /**
     * 客户端关闭连接后调用
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //解决空指针问题
        Connection connection = ctx.channel().attr(IOTConst.NETTY_CHANNEL_KEY).get();
        if (null == connection) {
            super.channelInactive(ctx);
            return;
        }
        ctx.close();
        //从连接池中删除该连接
        ConnectionHandler.removeConnection(ctx.channel().attr(IOTConst.NETTY_CHANNEL_KEY).get());
        log.info("{} connect close",ctx.channel());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        IdleStateEvent event = (IdleStateEvent)evt;
        String eventType = null;
        switch (event.state()){
            case READER_IDLE:
                eventType = "读空闲";
                readIdleTimes ++; // 读空闲的计数加1
                break;
            case WRITER_IDLE:
                eventType = "写空闲";
                // 不处理
                break;
            case ALL_IDLE:
                eventType ="读写空闲";
                // 不处理
                break;
            default:
                // 不处理
                break;
        }

        //解决空指针问题
        Connection connection = ctx.channel().attr(IOTConst.NETTY_CHANNEL_KEY).get();
        if (null == connection) {
            super.channelInactive(ctx);
            return;
        }

        log.warn("{} HeartBeat Timeout Event is :{}",ctx.channel().remoteAddress(),eventType);
//        if(readIdleTimes > 2){
//
//        }
        log.warn("HeartBeat Timeout,connect will be removed,channel:{}",ctx.channel().remoteAddress());
        ctx.channel().writeAndFlush("you are out");
        ctx.channel().close();
        ConnectionHandler.removeConnection(ctx.channel().attr(IOTConst.NETTY_CHANNEL_KEY).get());
    }

    /**
     * 发生异常触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("{}-Exception:{}", ctx,cause);
        cause.printStackTrace();
        ctx.close();
    }
}

package com.chaining.iot.nettyserver.netty;

import com.chaining.iot.nettyserver.handler.DecodeHandler;
import com.chaining.iot.nettyserver.handler.EncodeHandler;
import com.chaining.iot.nettyserver.handler.NettyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @program: wxstcgateway
 * @ClassName ServerChannelInitializer
 * @description: netty服务初始化工作
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-10 22:45
 * @Version 1.0
 **/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //添加解码器
        socketChannel.pipeline().addLast("decoder",new DecodeHandler());
        //添加编码器
        socketChannel.pipeline().addLast("encoder",new EncodeHandler());
        //心跳
        //socketChannel.pipeline().addLast(new IdleStateHandler(20, 0, 0, TimeUnit.SECONDS));
        //通过 NettyServerHandler 实例给每一个新的 Channel 初始化
        socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}

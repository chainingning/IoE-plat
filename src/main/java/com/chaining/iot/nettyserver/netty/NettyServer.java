package com.chaining.iot.nettyserver.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @program: wxstcgateway
 * @ClassName NettyServer
 * @description: netty 服务
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-10 19:25
 * @Version 1.0
 **/
@Component
public class NettyServer {
    private static final Logger log = LoggerFactory.getLogger(NettyServer.class);

    private final int port = 7777;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel serverChannel;

    public NettyServer() {
        //创建一个主线程组
        bossGroup = new NioEventLoopGroup();
        //创建一个工作线程组
        workerGroup = new NioEventLoopGroup();
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerChannelInitializer())
                //设置队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.TCP_NODELAY, true);
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            log.info("Iot started: {}", future.channel().localAddress());
            future.channel().closeFuture().sync();
            serverChannel = future.channel();
        } catch (InterruptedException e) {
            log.info("server Exception", e);
        } finally {
            stop();
        }
    }

    private void stop() {
        if (serverChannel != null) {
            serverChannel.close();
        }
//        if (workerGroup != null) {
//
//        }
        //关闭工作线程组
        workerGroup.shutdownGracefully();
        //关闭主线程组
        bossGroup.shutdownGracefully();
        log.info("Server is shut down");
    }
}

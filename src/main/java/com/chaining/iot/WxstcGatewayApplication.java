package com.chaining.iot;

import com.chaining.iot.nettyserver.netty.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * {DataSourceAutoConfiguration}的作用：
 * 项目需要自定义数据源的时候，或者不想系统自动注入数据源的时候，就把它排除掉。
 * 应用的场景比如多数据源进行动态的切换等等。
 * @program: wxstcgateway
 * @ClassName WxstcGatewayApplication
 * @description:
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-10 18:56
 * @Version 1.0
 **/
@SpringBootApplication
@MapperScan("com.chaining.iot.webserver.mapper")
public class WxstcGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxstcGatewayApplication.class);
        run();
    }

    private static NettyServer nettyServer = new NettyServer();

    /**
     * netty线程，web为主线程
     */
    private static void run() {
        new Thread(() -> {
            nettyServer.start();
        }).start();
    }
}

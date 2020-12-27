package com.chaining.iot.nettyserver.process;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 发送iot数据给小程序线程池
 *
 * @program: wxstcgateway
 * @ClassName IotDataSendExectorManager
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-17 23:39
 * @Version 1.0
 **/
public class IotDataSendExectorManager {
    public static IotDataSendExectorManager getInstance() {
        return IDE;
    }

    public <T extends Runnable> void execute(T task) {
        threadPool.execute(task);
    }

    private IotDataSendExectorManager() {
        threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    private static IotDataSendExectorManager IDE = new IotDataSendExectorManager();

    private ThreadPoolExecutor threadPool;
}

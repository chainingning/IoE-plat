package com.chaining.iot.nettyserver.process;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 发送线程池管理
 *
 * @program: wxstcgateway
 * @ClassName SendExecutorManager
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-28 21:52:10
 * @Version 1.0
 **/
public class SendExecutorManager {

    private static SendExecutorManager EM = new SendExecutorManager();

    private ThreadPoolExecutor threadPool;

    private SendExecutorManager() {
        threadPool = new ThreadPoolExecutor(30, 30,
                5L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    public static SendExecutorManager getInstance() {
        return EM;
    }

    public <T extends Runnable> void execute(T task) {
        threadPool.execute(task);
    }

}

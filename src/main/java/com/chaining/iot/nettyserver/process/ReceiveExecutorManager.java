package com.chaining.iot.nettyserver.process;

import java.util.concurrent.*;


/**
 * @author ningc
 */
public class ReceiveExecutorManager {

    public static ReceiveExecutorManager getInstance() {
        return EM;
    }

    public <T extends Runnable> void execute(T task) {
        threadPool.execute(task);
    }

    private ReceiveExecutorManager() {
        /**
         *
         * 创建一个CachedThreadPool线程池，corePoolSize = 0，maximumPoolSize = Integer.MAX_VALUE，即线程数量几乎无限制；
         * keepAliveTime = 30s，线程空闲30s后自动结束。
         * workQueue 为 SynchronousQueue 同步队列，这个队列类似于一个接力棒，入队出队必须同时传递，因为CachedThreadPool线程创建无限制，不会有队列等待，所以使用SynchronousQueue；
         *
         * NIO接受请求时，需要快速处理大量耗时较短的任务，因此使用CachedThreadPool
         */
        threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                30L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    private static ReceiveExecutorManager EM = new ReceiveExecutorManager();

    private ThreadPoolExecutor threadPool;


}

package com.test.Strategy;

/**
 * 上下文
 *
 * @program: wxstcgateway
 * @ClassName Context
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-31 22:13
 * @Version 1.0
 **/
public class Context {
    Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    //上下文接口
    public void contextInterface() {
        strategy.algorithmInterface();
    }
}

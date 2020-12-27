package com.test.Strategy;

/**
 * TODO
 *
 * @program: wxstcgateway
 * @ClassName Client
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-31 22:40
 * @Version 1.0
 **/
public class Client {
    public static void main(String[] args) {
        Context context ;
        context = new Context(new ConcreteStrategyA());
        context.contextInterface();
    }
}

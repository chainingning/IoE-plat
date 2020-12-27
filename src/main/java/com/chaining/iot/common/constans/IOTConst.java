package com.chaining.iot.common.constans;

import com.chaining.iot.common.entity.Connection;
import io.netty.util.AttributeKey;

/**
 * iot系统常量
 *
 * @program: wxstcgateway
 * @ClassName IOTConst
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-11 14:34
 * @Version 1.0
 **/
public class IOTConst {

    public static final AttributeKey<Connection> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");

    /**
     * 报文最短长度
     */
    public static final int ACCEPT_MIN_MSG_LENGTH = 9;

    /**
     * 接收报文最大长度
     */
    public static final int ACCEPT_MAX_MSG_LENGTH=1043;

    /**
     * 空闲时间，单位:秒
     */
    public static final int MAX_IDLE_TIME = 420;

    /**
     * 终端请求注册
     */
    public static final byte REQ01 = 0x01;

    /**
     * 终端请求注销
     */
    public static final byte REQ02 = 0x02;

    /**
     * 终端自动发送的心跳包
     */
    public static final byte HEARTBEAT =(byte)0x03;

    /**
     * 接收到DSC数据中心的用户数据的应答包
     */
    public static final byte REQ05 = 0x05;

    /**
     * 发送给DSC数据中心的终端DTU数据包
     */
    public static final byte REQ06 = 0x06;

    /**
     * 固定帧头帧尾
     */
    public static final byte DEFAULT_DATA = 0x7B;

}

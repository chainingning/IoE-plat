package com.chaining.iot.nettyserver.handler;

import com.chaining.iot.common.entity.Connection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 连接处理类
 *
 * @program: wxstcgateway
 * @ClassName ConnectionHandler
 * @author: ning.chai@foxmail.com
 * @create: 2020-03-11 21:54
 * @Version 1.0
 **/
public class ConnectionHandler {

    public static ConcurrentHashMap<String, Connection> connections = new ConcurrentHashMap<String, Connection>();

    /**
     * 注册链接
     * @param conn ctx中的连接
     */
    public static void addConnection(Connection conn) {
        connections.put(conn.getIdentityId(), conn);
    }

    /**
     * 根据IdentityId获取连接通道
     * @param conn
     * @return
     */
    public static Connection getConnection(Connection conn) {
        return connections.get(conn.getIdentityId());
    }

    /**
     *删除连接
     * @param conn
     */
    public static void removeConnection(Connection conn) {
        //判断channel
        if (conn.equals(getConnection(conn))) {
            connections.remove(conn.getIdentityId());
        }
    }

    /**
     * 根据idenitity获取连接
     * @param idenitity
     * @return
     */
    public static Connection getConnection(String idenitity) {
        return connections.get(idenitity);
    }

    /**
     * 查询设备是否在线
     * @param idenitity
     * @return
     */
    public static boolean isOnline(String idenitity) {
        return connections.containsKey(idenitity);
    }


}

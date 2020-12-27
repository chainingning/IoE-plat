package com.chaining.iot.wxserver.websocket;

import com.chaining.iot.common.utils.Message;
import com.chaining.iot.common.utils.StringUtils;
import com.chaining.iot.framework.config.HttpSessionConfigurator;
import com.chaining.iot.wxserver.model.OnlineUser;
import com.chaining.iot.wxserver.model.User;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 小程序推送
 *
 * @program: wxstcgateway
 * @ClassName IOTSocketServer
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-12 22:18
 * @Version 1.0
 **/
@Slf4j
@ServerEndpoint(value = "iotSocket/{userid}",configurator = HttpSessionConfigurator.class)
public class IotSocketServer {
    private static int onlineCount = 0;
    /**
     * 在线用户
     */
    public static Map<String, OnlineUser> onlineUserMap = new ConcurrentHashMap<>();

    /**
     * 连接成功调用的方法
     * @param userid
     * @param session
     * @param config
     */
    @OnOpen
    public void onOpen(@PathParam("userid") String userid, Session session, EndpointConfig config){
        log.info("[ChatServer] connection : userid ={},sessionId={}", userid,session.getId());
        //增加
        addOnlineCount();

        //获取当前用户的session
        HttpSession httpSession  = (HttpSession)config.getUserProperties().get(HttpSession.class.getName());
        User user = (User)httpSession.getAttribute("user");

        //将当前用户添加到在线用户列表中
        OnlineUser onlineUser = new OnlineUser(user.getUserName(), user.getUserName(), session);
        onlineUserMap.put(userid,onlineUser);
    }

    /**
     * 连接关闭方法
     * @param userid
     * @param session
     * @param closeReason
     */
    @OnClose
    public void onClose(@PathParam("userid")String userid,Session session,CloseReason closeReason){
        log.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() +
                " , closeCode = " + closeReason.getCloseCode().getCode() + " , closeReason = " +closeReason.getReasonPhrase());

        // 减少当前用户
        subOnlineCount();
        // 移除的用户信息
        OnlineUser removeUser = onlineUserMap.remove(userid);
        onlineUserMap.remove(userid);
    }

    /**
     * 接收客户端的message，判断是否有接收人而选择进行广播还是指定发送
     * @param userid unitid
     * @param data 客户端发来的消息
     */
    public void onMessage(@PathParam("userid") String userid,String data){
        log.info("[ChatServer] onMessage : userid = " + userid + " , data = " + data);

    }

    /**
     * 发生错误
     * @param throwable
     */
    @OnError
    public void onError(@PathParam("userid") String userid,Session session,Throwable throwable){
        log.info("[ChatServer] close : userid = " + userid + " , sessionId = " + session.getId() +" , throwable = " + throwable.getMessage() );
    }

    public synchronized void addOnlineCount(){
        onlineCount++;
    }

    public synchronized void subOnlineCount(){
        onlineCount--;
    }

    private String userIdCaseNickName(String userId){
        if (StringUtils.isNotEmpty(userId)) {

        }
        return null;
    }

}

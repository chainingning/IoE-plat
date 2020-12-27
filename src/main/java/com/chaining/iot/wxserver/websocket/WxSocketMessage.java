package com.chaining.iot.wxserver.websocket;

import com.alibaba.fastjson.JSON;
import com.chaining.iot.wxserver.model.OnlineUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 小程序推送消息工具类
 *
 * @program: wxstcgateway
 * @ClassName WxSocketMessage
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-13 21:38
 * @Version 1.0
 **/
@Slf4j
public class WxSocketMessage {

    /**
     * 通知
     */
    public static String NOTICE = "notice";

    /**
     * 消息
     */
    public static String MESSAGE = "message";

    /**
     * 组装信息返回给前台
     *
     * @param message  交互信息
     * @param type     信息类型
     * @param userList 在线列表
     * @return "massage" : {
     * "from" : "xxx",
     * "to" : "xxx",
     * "content" : "xxx",
     * "time" : "xxxx.xx.xx"
     * },
     * "type" : {notice|message},
     * "list" : {[xx],[xx],[xx]}
     */
    public static String getMessage(String message, String type, Collection<OnlineUser> userList) {
        if (CollectionUtils.isEmpty(userList)) {

        }
        return null;
    }

    /**
     * 消息内容
     *
     * @param fromUser
     * @param to
     * @param content
     * @param time
     * @return {
     * "from" : "xxx",
     * "to" : "xxx",
     * "content" : "xxx",
     * "time" : "xxxx.xx.xx"
     * }
     */
    public static String getContent(OnlineUser fromUser, String to, String content, String time) {
        return null;
    }

    /**
     * 广播消息
     *
     * @param unitId      消息
     * @param onlineUsers 在线用户
     */
    public static void broadcast(String unitId, Object message, Collection<OnlineUser> onlineUsers) {
        //对在线用户进行广播
        onlineUsers.stream().filter(user->unitId.equals(user.getUserId())).collect(Collectors.toList()).forEach(u->{
            try {
                u.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
            } catch (IOException e) {
                e.printStackTrace();
                log.warn("给用户[{}]发送消息失败", u.getNickname());
                //lambda表达式中使用return时，这个方法是不会返回的，而只是执行下一次遍历
                return;
            }
        });

//        for (OnlineUser user : onlineUsers) {
//            if (user.getUserId().equals(unitId)) {
//                try {
//                    user.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    log.warn("给用户[{}]发送消息失败", user.getNickname());
//                    //继续循环，不影响其他用户
//                    continue;
//                }
//            }
//        }
    }

    /**
     * 对特定用户发送消息
     *
     * @param message
     * @param onlineUser
     */
    public static void singleSend(String unitId, Object message, OnlineUser onlineUser) {
        log.info("[singleSend] message = " + JSON.toJSONString(message) + ", toUser = " + onlineUser.getNickname());
        try {
            onlineUser.getSession().getBasicRemote().sendText(JSON.toJSONString(message));
        } catch (IOException e) {
            e.printStackTrace();
            log.warn("给用户[{}]发送消息失败", onlineUser.getNickname());
        }
    }


}

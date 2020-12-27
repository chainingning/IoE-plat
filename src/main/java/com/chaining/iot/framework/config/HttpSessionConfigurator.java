package com.chaining.iot.framework.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * ccq
 *
 * @program: wxstcgateway
 * @ClassName HttpSessionConfigurator
 * @author: ning.chai@foxmail.com
 * @create: 2020-04-12 22:16
 * @Version 1.0
 **/
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(), session);
    }
}

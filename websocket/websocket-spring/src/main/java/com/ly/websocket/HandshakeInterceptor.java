package com.ly.websocket;

import org.apache.log4j.Logger;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    private static final Logger log = Logger.getLogger(HandshakeInterceptor.class);
    /**
     * 握手前
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        log.info("++++++++++++++++++++++ HandshakeInterceptor: beforeHandshake  ++++++++++++++"+attributes);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    /**
     *握手后
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        log.info("++++++++++++++++ HandshakeInterceptor: afterHandshake  ++++++++++++++");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}

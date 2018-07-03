package com.ly.websocket;

import org.apache.log4j.Logger;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;


public class MyWebSocketHandler implements WebSocketHandler {
    private static final Logger log = Logger.getLogger(MyWebSocketHandler.class);

    //报存所有用户的session
    private static final ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();

    /**
     * 连接就绪时
     *
     * @param webSocketSession
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("connect wewbsocket success");
        users.add(webSocketSession);
    }

    /**
     * 处理信息
     *
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("handleMessage......." + webSocketMessage.getPayload() + "..........." + webSocketMessage.getPayload().toString());

        //处理消息msgContent的内容
        TextMessage textMessage = new TextMessage(webSocketMessage.getPayload().toString());
        //发送消息给所有人
        sendMsgToAllUsers(textMessage);
    }

    //给所有用户发送消息
    private void sendMsgToAllUsers(TextMessage textMessage) throws IOException {
        for (WebSocketSession socketSession : users) {
            socketSession.sendMessage(textMessage);
        }
    }

    /**
     * 处理传输时异常
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    /**
     * 关闭连接时
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        log.info("connect websocket closed.......");
        users.remove(webSocketSession);
    }

    public boolean supportsPartialMessages() {
        return false;
    }
}

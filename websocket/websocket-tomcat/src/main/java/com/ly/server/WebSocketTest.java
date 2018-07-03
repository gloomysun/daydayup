package com.ly.server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ServerEndpoint 是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端
 * 注解的值将被用作监听用户连接的终端访问url地址，客户端可以通过这个url来连接到WebSocket服务器端
 */
@ServerEndpoint("/websocket")
public class WebSocketTest {
    //静态变量，用来记录当前在线连接数，应该把它设计成线程安全的
    private static AtomicInteger onlineCount = new AtomicInteger(0);
    //private static Integer onlineCount=0;

    //concurrent包的线程安全set，用来存放每个客户端对应的WebSocket对象。若要实现服务端与单一客户端通信的话，可以用map存放
    //其中key 为用户标识
    private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
    //与某个客户端的连接会话，需要通过它给客户端发送数据
    private Session session;

    @OnOpen
    public void onOpen(Session session) throws InterruptedException {
        this.session=session;
        webSocketSet.add(this);
        onlineCount.getAndIncrement();
        //onlineCount++;
        System.out.println(Thread.currentThread().getName()+"=="+this+"有新连接加入！当前人数："+onlineCount.get());
    }

    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);
        onlineCount.getAndDecrement();
        //onlineCount--;
        System.out.println(Thread.currentThread().getName()+"=="+this+"有连接断开！当前人数："+onlineCount.get());
    }

    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息:" + message);
        //群发消息
        for(WebSocketTest item:webSocketSet){
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendMessage(String message) throws IOException {
        System.out.println(this.session+"发送消息:"+message);
        this.session.getBasicRemote().sendText(message);
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

}

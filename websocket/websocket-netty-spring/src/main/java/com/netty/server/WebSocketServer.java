package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class WebSocketServer {
    @Autowired
    private EventLoopGroup bossGroup;
    @Autowired
    private EventLoopGroup workerGroup;
    @Autowired
    private ServerBootstrap serverBootstrap;

    @Autowired
    @Qualifier("webSocketServerInitializer")
    private ChannelInitializer webSocketServerInitializer;
    private ChannelFuture channelFuture;
    //服务端口
    @Value("${netty.server.port}")
    private int port;

    public void build(int port){
        try {
            /**
             * 1.boss辅助客户端的tcp连接请求，worker负责与客户端之前的读写操作
             * 2.配置客户端的channel类型
             * 3.配置tcp参数，握手字符串长度设置
             * 4.TCP_NODELAY是一种算法，为了充分利用k廗，尽可能发送大块数据，减少充斥的小数据，true是关闭，可以保持高实时性
             *  若开启，减少交互次数，但是时效性相对无法保证
             * 5.开启心跳包活机制，就是客户端，服务端建立处于ESTABLISHED状态，超过2小时没有交流，机制会被启动
             * 6.netty提供了两种接收缓存分配器，FixedRecvByteBufAllocator是固定长度，但是拓展，AdaptiveRecvByteBufAllocator动态长度
             * 7.绑定IO事件的处理类，WebSocketChildChannelHandler中定义
             */
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childOption(ChannelOption.RCVBUF_ALLOCATOR,new FixedRecvByteBufAllocator(592048))
                    .childHandler(webSocketServerInitializer);
            System.out.println("成功");
            channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
    @PreDestroy
    public void close(){
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    @PostConstruct
    public void initNetty() {
        new Thread(new Runnable() {
            public void run() {
                build(port);
            }
        }).start();
    }
}

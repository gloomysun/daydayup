package com.ly._01discard;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 丢弃任何进入的数据，启动服务端的DiscardServerHandler
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() {
        /**
         * bossGroup用来接收进来的连接
         * workerGroup用来处理已经被接收的连接，一旦boss接收到连接，就会把连接信息注册到worker上
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        System.out.println("准备运行端口" + port);
        try {
            /**
             *  ServerBootStrap是一个启动NIO服务的辅助启动类，你可以在这个服务中直接使用Channel
             */
            ServerBootstrap b = new ServerBootstrap();
            /**
             * 绑定两个线程组 没有设置group会报java.lang.IllegalStateException: group not set异常
             */
            b = b.group(bossGroup, workGroup);
            /**
             * 指定Nio的模式
             */
            b = b.channel(NioServerSocketChannel.class);
            /**
             * 这里的事件
             */
            b = b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new DiscardServerHandler());
                }
            });
            /**
             * 服务端TCP内核维护两个队列，Syn队列和Accept队列，Syn队列是指存放完成第一次握手的连接，Accept队列是存放完成
             * 整个TCP三次握手的连接，内核会根据somaxconn和backlog的较小值设置accept queue的大小
             * 当两个队列长度之和大于ChannelOption.SO_BACKLOG时，新的连接将会被TCP内核拒绝
             */
            b = b.option(ChannelOption.SO_BACKLOG, 128);//设置TCP缓冲区
            /**
             * option()是提供给NioServerSocketChannel用来接收进来的连接,也就是boss线程。
             * childOption()是提供给由父管道ServerChannel接收到的连接,也就是worker线程
             */
            b = b.childOption(ChannelOption.SO_KEEPALIVE, true);
            /**
             * 绑定端口并启动去接收进来的连接
             */
            ChannelFuture f = b.bind(port).sync();
            /**
             *这里会一直等待，直到socket被关闭
             */
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            /**
             * 关闭
             */
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    /**
     * 将规则跑起来
     * @param args
     */
    public static void main(String[] args) {
        int port;
        if(args.length>0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 9999;
        }
        new DiscardServer(port).run();
        System.out.println("server:run()");
    }
}

package com.ly._02delimiter;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Server {
    private int port;
    private static final String DELIMITER = "_$";

    public Server(int port) {
        this.port = port;
    }

    public void run() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());        // 获取特殊分隔符的ByteBuffer
                            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(128, delimiter)); // 设置特殊分隔符用于拆包
                            socketChannel.pipeline().addLast(new StringDecoder());                // 设置字符串形式的解码
                            socketChannel.pipeline().addLast(new ServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);  // childOption会在客户端成功connect后才执行，设置保持连接
            ChannelFuture future = b.bind(port).sync(); //绑定端口进行监听
            System.out.println("服务端启动：" + port);
            future.channel().closeFuture().sync();//阻塞
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 9999;
        }
        new Server(port).run();
    }
}

package com.ly._3marshaling;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MarshallingClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9999;

    private static EventLoopGroup group;
    private Bootstrap bootstrap;
    private ChannelFuture future;

    private static class SingletonHolder {
        static final MarshallingClient client = new MarshallingClient();
    }

    public static MarshallingClient getInstance() {
        return SingletonHolder.client;
    }

    private MarshallingClient() {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        try {
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
                            socketChannel.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
                            socketChannel.pipeline().addLast(new MarshallingClientHandler());
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            future = bootstrap.connect(HOST, PORT).sync();
            System.out.println("连接远程服务器......");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChannelFuture getChannelFuture() {
        if (this.future == null || !this.future.channel().isActive()) {
            this.connect();
        }
        return this.future;
    }

    public static void main(String[] args) {
        final MarshallingClient client = getInstance();
        try {
            ChannelFuture future = client.getChannelFuture();
            for (int i = 0; i < 3; i++) {
                Req req = new Req();
                req.setId(i + "");
                req.setName("request" + i);
                req.setRequestMessage("NO " + i + " request");
                future.channel().writeAndFlush(req);
                TimeUnit.SECONDS.sleep(6);
            }
            future.channel().closeFuture().sync();


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("进入子线程");
                        ChannelFuture cf = client.getChannelFuture();
                        System.out.println("连接是否活跃  : " + cf.channel().isActive());
                        System.out.println("连接是否打开  : " + cf.channel().isOpen());
                        Req req = new Req();
                        req.setId(4 + "");
                        req.setName("picture");
                        req.setRequestMessage("子线程");

                        //路径path自定义
                        String path = System.getProperty("user.dir") + File.separatorChar + "Netty" + File.separatorChar +
                                "resources" + File.separatorChar + "001.jpg";
                        System.out.println(path);
                        File file = new File(path);
                        FileInputStream in = new FileInputStream(file);
                        byte[] bytes = new byte[in.available()];
                        in.read(bytes);
                        in.close();
                        req.setAttachment(GzipUtils.gzip(bytes));
                        System.out.println(req);
                        cf.channel().writeAndFlush(req);
                        cf.channel().closeFuture().sync();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            System.out.println("断开连接,主线程结束.....");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //group.shutdownGracefully();
        }

    }
}

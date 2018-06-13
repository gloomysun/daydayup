package com.ly._02delimiter;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
    private static final Integer PORT = 9999;
    private static final String HOST = "127.0.0.1";
    private static final String DELIMITER = "_$"; // 拆包分隔符

    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer(DELIMITER.getBytes());
                            //设置特殊分割符
                             socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(128, delimiter));
                            // 设置指定长度分割  不推荐，两者选其一
                            //   socketChannel.pipeline().addLast(new FixedLengthFrameDecoder(8));
                            //设置字符串形式的解码
                            socketChannel.pipeline().addLast(new StringDecoder());
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future = b.connect(HOST, PORT).sync();

            future.channel().writeAndFlush(Unpooled.copiedBuffer(("222" + DELIMITER).getBytes()));
            future.channel().writeAndFlush(Unpooled.copiedBuffer(("888" + DELIMITER).getBytes()));
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }

    }
}

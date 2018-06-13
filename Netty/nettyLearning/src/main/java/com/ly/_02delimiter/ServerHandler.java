package com.ly._02delimiter;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class ServerHandler extends ChannelHandlerAdapter{

    private static final String DELIMITER = "_$"; // 拆包分隔符
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            //do something msg
          /* 设置字符串形式的解码 new StringDecoder() 后可以直接使用
             ByteBuf buf = (ByteBuf)msg;
                byte[] data = new byte[buf.readableBytes()];
                buf.readBytes(data);
                String request = new String(data, "utf-8");
                System.out.println("Server: " + request);
             */

            //写给客户端
            System.out.println("Netty Server : " + msg.toString());
            String response = msg.toString()+ DELIMITER;
            ctx.channel().writeAndFlush(Unpooled.copiedBuffer(response.getBytes()));  // 数据是写入到Buffer缓冲中，需要flush一下
        }finally {
            ReferenceCountUtil.release(msg);//有上一句话，即ctx.writeAndFlush()，这里可以不关闭
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

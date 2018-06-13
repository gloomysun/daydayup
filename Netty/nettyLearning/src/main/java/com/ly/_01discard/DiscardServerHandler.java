package com.ly._01discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

public class DiscardServerHandler extends ChannelHandlerAdapter{

    /**
     * 每当客户端收到新数据时，调用这个方法
     * @param ctx 通道处理的上下文信息
     * @param msg 接收的消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf in = (ByteBuf) msg;



           System.out.print(in.toString(CharsetUtil.UTF_8));

        } finally {
            /**
             * ByteBuf是一个引用计数对象，这个对象必须显示的调用release方法来释放
             * 处理器的职责是释放所有传递到处理器的引用计数对象
             */
            ReferenceCountUtil.release(msg);
        }


    }

    /**
     * 这个方法在发生异常时触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**
         * exceptionCaught()事件处理方法是当出现Throwable对象才会被调用，即当netty由于IO
         * 错误或者处理器在处理事件时抛出的异常。
         */
        //出现异常就关闭
        System.out.println("出错了");
        cause.printStackTrace();
        ctx.close();
    }
}

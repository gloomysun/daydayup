package com.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

public abstract class BaseWebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
    /**
     * 推送单个
     */
    public static void push(final ChannelHandlerContext ctx, final String message) {
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        ctx.channel().writeAndFlush(tws);
    }

    /**
     * 群发
     */
    public static void push(final ChannelGroup ctxGroup, final String message) {
        TextWebSocketFrame tws = new TextWebSocketFrame(message);
        ctxGroup.writeAndFlush(tws);
    }
}

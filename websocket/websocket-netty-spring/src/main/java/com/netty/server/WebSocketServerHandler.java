package com.netty.server;

import com.alibaba.fastjson.JSONObject;
import com.netty.constant.Constant;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class WebSocketServerHandler extends BaseWebSocketServerHandler {

    private WebSocketServerHandshaker handshaker;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        push(ctx, "连接成功");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        for (String key : Constant.pushCtxMap.keySet()) {
            if (ctx.equals(Constant.pushCtxMap.get(key))) {
                //从连接池内剔除
                System.out.println(Constant.pushCtxMap.size());
                System.out.println("剔除" + key);
                Constant.pushCtxMap.remove(key);
                System.out.println(Constant.pushCtxMap.size());
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            handlerHttpRequest(ctx, (FullHttpRequest) msg);
        } else if (msg instanceof WebSocketFrame) {
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }


    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        //关闭
        if (msg instanceof CloseWebSocketFrame) {
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) msg.retain());
            return;
        }
        if (msg instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
            return;
        }
        if (msg instanceof PongWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(msg.content().retain()));
            return;
        }
        //只支持文本格式，不支持二进制
        if (!(msg instanceof TextWebSocketFrame)) {
            throw new Exception("只支持文本格式");
        }
        //客户端发送过来的消息
        String request = ((TextWebSocketFrame) msg).text();
        System.out.println("服务端收到:" + request);

        JSONObject jsonObject = null;
        jsonObject = JSONObject.parseObject(request);
        System.out.println(jsonObject.toJSONString());

        if (jsonObject == null) {
            return;
        }
        String id = (String) jsonObject.get("id");
        String type = (String) jsonObject.get("type");
        String msgContent = (String) jsonObject.get("msgContent");

        //根据id判断是否登录或者是否有权限
        if (id != null && !"".equals(id) && type != null && !"".equals(type)) {
            //用户是否有权限
            boolean idAccess = true;
            //判断是否符合定义
            boolean typeAccess = true;
            if (idAccess && typeAccess) {
                System.out.println("添加到连接池");
                Constant.pushCtxMap.put(id, ctx);
                //根据type存放进对应的channel池，这里简单实现，直接放进aaChannelGroup，方便群发
                Constant.aaChannelGroup.add(ctx.channel());
                push(Constant.aaChannelGroup, "欢迎" + id + "登陆");
            }
        } else {
            push(Constant.aaChannelGroup, String.format("%s:%s", id, msgContent
            ));
        }

    }

    //第一次请求是http请求，请求头包括ws 的信息
    private void handlerHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        if (!req.decoderResult().isSuccess()) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
        }

        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws:/" + ctx.channel() + "/websocket", null, false);
        handshaker = wsFactory.newHandshaker(req);
        if (handshaker == null) {
            //不支持
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }

    private void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        //返回应答给客户端
        if (res.status().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        //如果是非keepalive 关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package com.ly._3marshaling;


import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MarshallingServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Netty Server active ......");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            Req req = (Req) msg;
            System.out.println("Server:" + req.toString());
            //处理数据并返回给客户端
            Resp resp = new Resp();
            resp.setId(req.getId());
            resp.setName(req.getName() + "-SUCCESS");
            resp.setResponseMessage(req.getRequestMessage() + "-SUCCESS");
            //如果有附件则保存附件
            if (null != req.getAttachment()) {
                byte[] attachment = GzipUtils.ungzip(req.getAttachment());
                String path = System.getProperty("user.dir") + File.separatorChar +
                        "Netty" + File.separatorChar + "receive" + File.separatorChar
                        + System.currentTimeMillis() + ".jpg";
                FileOutputStream outputStream = new FileOutputStream(path);
                outputStream.write(attachment);
                outputStream.close();
                resp.setResponseMessage("file upload success,file path is：" + path);
            }
            ctx.writeAndFlush(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

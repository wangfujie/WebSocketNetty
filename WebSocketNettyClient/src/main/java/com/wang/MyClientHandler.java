package com.wang;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

/**
 * @Author wangfj_tongtech
 * @DATE 2020/1/10
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            ByteBuf bb = (ByteBuf)msg;
            byte[] respByte = new byte[bb.readableBytes()];
            bb.readBytes(respByte);
            String respStr = new String(respByte);
            System.err.println("client--收到响应：" + respStr);
        } finally{
            // 必须释放msg数据
            ReferenceCountUtil.release(msg);
        }
    }
}

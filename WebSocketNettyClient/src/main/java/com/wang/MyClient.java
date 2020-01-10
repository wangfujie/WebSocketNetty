package com.wang;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * @Author wangfj_tongtech
 * @DATE 2020/1/10
 */
public class MyClient {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new MyClientHandler());
                }
            });
            //客户端开启
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 8888);
            // 发送客户端的请求
            connect.channel().writeAndFlush(Unpooled.copiedBuffer("request".getBytes()));
            // 等待直到连接中断
            connect.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //优雅的退出程序
            bossGroup.shutdownGracefully();
        }
    }
}

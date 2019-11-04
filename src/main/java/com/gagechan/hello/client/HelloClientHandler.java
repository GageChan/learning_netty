package com.gagechan.hello.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalDateTime;

public class HelloClientHandler extends SimpleChannelInboundHandler<String> {

    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output:"+msg);
        Thread.sleep(3_000);
        ctx.writeAndFlush("from client:"+ LocalDateTime.now());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("from client:"+ LocalDateTime.now());
    }
}

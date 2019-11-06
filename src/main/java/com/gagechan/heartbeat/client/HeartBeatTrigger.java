package com.gagechan.heartbeat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatTrigger extends ChannelInboundHandlerAdapter {

    public static final String HEART_BEAT = "heart beat";

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE){
                ctx.channel().writeAndFlush(HEART_BEAT);
            }
        }else {
            super.userEventTriggered(ctx, evt);
        }
    }
}

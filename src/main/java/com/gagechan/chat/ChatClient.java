package com.gagechan.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatClient {

    public static void main(String[] args) throws Exception{
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 1321).sync().channel();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;) {
                channel.writeAndFlush(br.readLine()+"\r\n");
            }

        } finally {
            group.shutdownGracefully();
        }
    }

}

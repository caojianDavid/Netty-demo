package com.pjqdyd.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**   
 * @Description:  [netty聊天客户端]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyChatClient {

    public static void main(String[] args) throws Exception{
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            //一直读取键盘输入
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            for (;;){
                //发送消息
                channel.writeAndFlush(br.readLine() + "\r\n");
            }
        }finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

}

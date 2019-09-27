package com.pjqdyd.ping.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**   
 * @Description:  [自定义初始器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyPingServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        //添加netty提供的一个空闲检测的handler
        pipeline.addLast(new IdleStateHandler(5,7,10, TimeUnit.SECONDS));

        pipeline.addLast(new MyPingServerHandler());
    }
}

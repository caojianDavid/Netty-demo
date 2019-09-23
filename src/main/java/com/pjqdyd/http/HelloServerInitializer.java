package com.pjqdyd.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**   
 * @Description:  [服务的初始化器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {

    //连接一旦被注册就会执行这个方法
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline(); //管道

        pipeline.addLast("httpServerCodec", new HttpServerCodec());

        //把自定义处理器添加到管道中
        pipeline.addLast("helloHttpServerHandler", new HelloHttpServerHandler());
    }
}

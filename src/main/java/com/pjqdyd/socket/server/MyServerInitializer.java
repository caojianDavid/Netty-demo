package com.pjqdyd.socket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**   
 * @Description:  [自定义netty服务的初始化器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyServerInitializer extends ChannelInitializer<SocketChannel> {

    //覆盖初始channel
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //获取channel管道
        ChannelPipeline pipeline = ch.pipeline();
        //添加编解码器
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        //添加自定义的handler处理
        pipeline.addLast(new MyServerHandler());
    }
}

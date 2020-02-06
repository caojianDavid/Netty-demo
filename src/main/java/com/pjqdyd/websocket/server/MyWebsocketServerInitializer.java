package com.pjqdyd.websocket.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**   
 * @Description:  [websocket server的初始化器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyWebsocketServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpServerCodec()); //websocket基于http的, 添加http的编解码器
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(8192)); //聚合分段的http请求/响应的处理器
        //添加websocket处理器, 负责websocket牵手, 心跳, 协议等等复杂的操作
        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast(new MyWebsocketServerHandler());
    }
}

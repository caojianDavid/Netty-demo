package com.pjqdyd.helloworld;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**   
 * @Description:  [自定义处理器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class HelloHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    //接收读取客户端发送的请求, 并响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //if(msg instanceof HttpRequest){//包裹下面代码}

        //定义响应的内容
        ByteBuf content = Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8);
        //响应体
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, content);
        //设置响应头
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

        //发送响应
        ctx.writeAndFlush(response);
    }
}

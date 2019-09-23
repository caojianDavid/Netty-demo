package com.pjqdyd.socket.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**   
 * @Description:  [自定义客户端处理器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    //连接一旦建立激活执行的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //向服务端发送
        ctx.writeAndFlush("来自client客户:hello!");
    }

    //接收服务端的响应
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到服务端"+ ctx.channel().remoteAddress() + "的回复:" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

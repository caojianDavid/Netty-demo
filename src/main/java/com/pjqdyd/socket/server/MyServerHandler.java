package com.pjqdyd.socket.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**   
 * @Description:  [自定义服务端的handler处理器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收连接并处理
     * @param ctx channel的上下文, 可以获取一些连接信息
     * @param msg 接收的消息
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("收到数据来自" + ctx.channel().remoteAddress() + "->" + msg);

        //响应消息
        ctx.channel().writeAndFlush("接收成功");
    }

    //出现异常执行的方法
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); //关闭连接
    }
}

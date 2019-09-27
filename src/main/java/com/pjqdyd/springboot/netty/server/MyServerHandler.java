package com.pjqdyd.springboot.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

/**   
 * @Description:  [自定义处理器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Component
public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    /**
     * 接收连接并处理
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

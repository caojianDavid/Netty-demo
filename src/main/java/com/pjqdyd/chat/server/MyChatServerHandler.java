package com.pjqdyd.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**   
 * @Description:  [服务端handler处理]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义channelGroup对象,用来保存连接进来的各个channel
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 连接一旦建立
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //获取新连接的channel
        Channel channel = ctx.channel();
        //广播客户端上线消息
        channelGroup.writeAndFlush("[服务器收到客户端]-" + channel.remoteAddress() + "加入\n");
        //将新连接channel加入group
        channelGroup.add(channel);
    }

    /**
     * 连接一旦激活
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "上线");
    }

    /**
     * 收到消息的处理方法
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.forEach(ch -> {
            if (channel != ch){ //如果当前channel连接不是自己的ch
                //向其他人发送消息
                ch.writeAndFlush(channel.remoteAddress() + "发送的消息" + msg + "\n");
            }else {
                //发消息给自己
                ch.writeAndFlush("自己发送的消息" + msg + "\n");
            }
        });
    }

    /**
     * 连接一旦失效
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + "下线");
    }

    /**
     * 连接一旦断开
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器收到客户端]-" + channel.remoteAddress() + "离开\n");
        //netty会自动移除
        //channelGroup.remove(channel);
    }

    /**
     * 出现异常的处理方法
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        ctx.close();
    }
}

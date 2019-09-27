package com.pjqdyd.ping.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

/**   
 * @Description:  [自定义处理器]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class MyPingServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){ //判断事件类型是否是空闲状态事件
            IdleStateEvent event = (IdleStateEvent) evt;
            String eventType = null;

            switch (event.state()){
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress() + "超时事件:" + eventType);
            ctx.channel().close();
        }
    }

}

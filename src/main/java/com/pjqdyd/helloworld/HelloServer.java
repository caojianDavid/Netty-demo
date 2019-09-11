package com.pjqdyd.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**   
 * @Description:  [测试netty服务, 运行此类, 访问localhost:8899]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

public class HelloServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(); //定义线程组bossGroup(事件死循环组)
        //bossGroup接收连接, 交由workerGroup来处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //启动服务管道, 添加自定义初始化器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new HelloServerInitializer());

            //绑定启动的端口
            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            //关闭监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            //安全关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}

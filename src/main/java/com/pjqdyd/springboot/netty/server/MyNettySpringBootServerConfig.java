package com.pjqdyd.springboot.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**   
 * @Description:  [netty服务客户端配置]
 * @Author:       pjqdyd
 * @Version:      [v1.0.0]
 */

@Configuration
public class MyNettySpringBootServerConfig implements CommandLineRunner {

    //注入自定义的初始化器
    @Autowired
    private MyServerInitializer myServerInitializer;

    @Bean(destroyMethod = "shutdownGracefully")
    EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(destroyMethod = "shutdownGracefully")
    EventLoopGroup workGroup() {
        return new NioEventLoopGroup();
    }

    /**
     * 为CommandLineRunner提供的覆盖方法, 会在spring应用启动后执行,启动netty服务
     */
    @Override
    public void run(String... args) throws Exception {
        int port = 8899;
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup(), workGroup()).channel(NioServerSocketChannel.class)
                .childHandler(myServerInitializer);

        serverBootstrap.bind(port).sync().addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("springboot-netty服务启动成功,正在监听: " + port);
            }
        });
    }
}

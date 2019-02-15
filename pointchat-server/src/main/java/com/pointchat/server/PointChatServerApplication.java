package com.pointchat.server;

import com.pointchat.common.codec.PacketDecodeHandler;
import com.pointchat.common.codec.PacketEncodeHandler;
import com.pointchat.server.config.BaseProperties;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PointChatServerApplication implements CommandLineRunner {

    @Autowired
    private BaseProperties baseProperties;

    private static final Logger LOG = LoggerFactory.getLogger(PointChatServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PointChatServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        // 配置服务启动器
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                     .channel(NioServerSocketChannel.class)
                     .childHandler(new ChannelInitializer<ServerSocketChannel>() {
                        @Override
                        protected void initChannel(ServerSocketChannel serverSocketChannel) {
                                serverSocketChannel.pipeline().addLast(new PacketDecodeHandler());

                                serverSocketChannel.pipeline().addLast(new PacketEncodeHandler());
                            }
                        });

            // 绑定端口，启动服务器
            int port = baseProperties.getPort();
            ChannelFuture future = bootstrap.bind(port).addListener(f  -> {
                if(f.isSuccess()){
                    LOG.info("Started point chat server with port " + port);
                }
            });

            // 异步阻塞等待服务停止
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}

package com.pointchat.client;

import com.pointchat.client.handler.RegisterResponseHandler;
import com.pointchat.client.handler.MessageResponseChannelHandler;
import com.pointchat.common.codec.PacketDecodeHandler;
import com.pointchat.common.codec.PacketEncodeHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientBootstrap {

    private String host;

    private int port;

    private ClientBootstrapConnectResult callBack;

    private MessageNotfiy messageNotfiy;

    public ClientBootstrap(String host, int port, ClientBootstrapConnectResult callBack, MessageNotfiy messageNotfiy){
        this.host = host;
        this.port = port;
        this.callBack = callBack;
        this.messageNotfiy = messageNotfiy;
    }

    public void start(){
        Bootstrap bootstrap = buildBootStrap(messageNotfiy);
        bootstrap.connect(host, port).addListener(f -> {
            if(f.isSuccess()){
                callBack.connectSuccess(((ChannelFuture)f).channel());
            } else {
                callBack.connectFail();
            }
        });
    }

    private Bootstrap buildBootStrap(MessageNotfiy messageNotfiy){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecodeHandler());
                        ch.pipeline().addLast(new RegisterResponseHandler(messageNotfiy));
                        ch.pipeline().addLast(new MessageResponseChannelHandler(messageNotfiy));
                        ch.pipeline().addLast(new PacketEncodeHandler());
                    }
                });

        return bootstrap;
    }

}

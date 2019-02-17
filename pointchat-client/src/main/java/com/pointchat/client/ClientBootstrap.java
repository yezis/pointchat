package com.pointchat.client;

import com.pointchat.common.codec.PacketDecodeHandler;
import com.pointchat.common.codec.PacketEncodeHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientBootstrap {

    private String host;

    private int port;

    public ClientBootstrap(String host){
        this.host = host;
    }

    public ClientBootstrap(String host, int port){
        this.host = host;
        this.port = port;
    }

    public void start(ClientBootstrapCallBack callBack){
        Bootstrap bootstrap = buildBootStrap();
        bootstrap.connect(host, port).addListener(f -> {
            if(f.isSuccess()){
                callBack.isSuccess();
            } else {
                callBack.isFail();
            }
        });
    }

    private Bootstrap buildBootStrap(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new PacketDecodeHandler());

                        ch.pipeline().addLast(new PacketEncodeHandler());
                    }
                });

        return bootstrap;
    }

}

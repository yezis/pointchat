package com.pointchat.server.handler;

import com.pointchat.common.protocol.AuthRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthChannelHandler extends SimpleChannelInboundHandler<AuthRequestPacket> {

    private static final Logger LOG = LoggerFactory.getLogger(AuthChannelHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AuthRequestPacket packet) {
        String token = packet.getToken();
        String username = packet.getUsername();
        LOG.info("token:" + token + "&username:" + username);

        // 调用logic 进行验证
    }

}

package com.pointchat.server.handler;

import com.pointchat.common.protocol.AuthRequestPacket;
import com.pointchat.common.protocol.AuthResponsePacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class AuthChannelHandler extends SimpleChannelInboundHandler<AuthRequestPacket> {

    private static final Logger LOG = LoggerFactory.getLogger(AuthChannelHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AuthRequestPacket packet) {
        String token = packet.getToken();
        String username = packet.getUsername();
        LOG.info("token:" + token + "&username:" + username);
        // TODO 调用logic 进行验证

        // 通知客户端身份验证结果
        AuthResponsePacket responsePacket = new AuthResponsePacket();
        responsePacket.setAuthSuccess(true);
        if(!username.equals("test") || !token.equals("123456")){
            responsePacket.setAuthSuccess(false);
            responsePacket.setReason("身份验证不通过");
        }
        ctx.channel().writeAndFlush(responsePacket);

    }

}

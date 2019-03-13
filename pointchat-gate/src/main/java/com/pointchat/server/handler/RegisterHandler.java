package com.pointchat.server.handler;

import com.pointchat.common.protocol.RegisterRequestPacket;
import com.pointchat.common.protocol.RegisterResponsePacket;
import com.pointchat.common.utils.Session;
import com.pointchat.common.utils.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class RegisterHandler extends SimpleChannelInboundHandler<RegisterRequestPacket> {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterRequestPacket packet) {
        String token = packet.getToken();
        String username = packet.getUsername();
        LOG.info("username:" + username + "&token:" + token + "&nickname:" + packet.getNickName());
        // TODO 调用logic 进行验证

        String userId = UUID.randomUUID().toString();
        Session session = new Session();
        session.setUserId(userId);
        session.setNickName(username);
        SessionUtil.bindSession(session, ctx.channel());

        // 通知客户端身份验证结果
        RegisterResponsePacket responsePacket = new RegisterResponsePacket();
        responsePacket.setAuthSuccess(true);
        responsePacket.setUserId(userId);
        ctx.channel().writeAndFlush(responsePacket);
    }

}

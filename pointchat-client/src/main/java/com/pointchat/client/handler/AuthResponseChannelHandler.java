package com.pointchat.client.handler;

import com.pointchat.client.MessageNotfiy;
import com.pointchat.common.protocol.AuthResponsePacket;
import com.pointchat.common.utils.LoginedUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class AuthResponseChannelHandler extends SimpleChannelInboundHandler<AuthResponsePacket> {

    private MessageNotfiy messageNotfiy;

    public AuthResponseChannelHandler(MessageNotfiy messageNotfiy){
        this.messageNotfiy = messageNotfiy;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AuthResponsePacket packet) throws Exception {
        if (packet.isAuthSuccess()){
            LoginedUtil.markLogined(ctx.channel());
        }
        messageNotfiy.authResult(packet, ctx);
    }

}

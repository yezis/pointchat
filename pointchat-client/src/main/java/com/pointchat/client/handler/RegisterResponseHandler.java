package com.pointchat.client.handler;

import com.pointchat.client.MessageNotfiy;
import com.pointchat.common.protocol.RegisterResponsePacket;
import com.pointchat.common.utils.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RegisterResponseHandler extends SimpleChannelInboundHandler<RegisterResponsePacket> {

    private MessageNotfiy messageNotfiy;

    public RegisterResponseHandler(MessageNotfiy messageNotfiy){
        this.messageNotfiy = messageNotfiy;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RegisterResponsePacket packet) throws Exception {
        if (packet.isAuthSuccess()){
            LoginUtil.markLogined(ctx.channel());
        }
        messageNotfiy.authResult(packet, ctx);
    }

}

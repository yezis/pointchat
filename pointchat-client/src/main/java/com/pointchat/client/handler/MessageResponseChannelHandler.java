package com.pointchat.client.handler;

import com.pointchat.client.MessageNotfiy;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseChannelHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {

    private MessageNotfiy messageNotfiy;

    public MessageResponseChannelHandler(MessageNotfiy messageNotfiy){
        this.messageNotfiy = messageNotfiy;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket packet) {
        messageNotfiy.messageResponse(packet, ctx);
    }

}

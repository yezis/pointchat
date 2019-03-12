package com.pointchat.client;

import com.pointchat.common.protocol.AuthResponsePacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;

public interface MessageNotfiy {

    public void authResult(AuthResponsePacket packet, ChannelHandlerContext ctx);

    public void messageResponse(MessageResponsePacket packet, ChannelHandlerContext ctx);

}

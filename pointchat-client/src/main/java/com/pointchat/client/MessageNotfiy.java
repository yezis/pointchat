package com.pointchat.client;

import com.pointchat.common.protocol.RegisterResponsePacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;

public interface MessageNotfiy {

    public void authResult(RegisterResponsePacket packet, ChannelHandlerContext ctx);

    public void messageResponse(MessageResponsePacket packet, ChannelHandlerContext ctx);

}

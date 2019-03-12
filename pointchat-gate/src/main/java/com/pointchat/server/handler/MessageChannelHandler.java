package com.pointchat.server.handler;

import com.pointchat.common.protocol.MessageRequestPacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageChannelHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static final Logger LOG = LoggerFactory.getLogger(MessageChannelHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        LOG.info("服务端接收到消息：" + packet.getMessage());

        Thread.sleep(3000);

        LOG.info("服务端接发消息消息：" + packet.getMessage());
        // 简单响应回发消息的客户端
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage("服务器跟你说：" + packet.getMessage());

        ctx.channel().writeAndFlush(responsePacket);
    }

}

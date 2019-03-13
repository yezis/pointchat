package com.pointchat.server.handler;

import com.pointchat.common.protocol.MessageRequestPacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import com.pointchat.common.utils.Session;
import com.pointchat.common.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageChannelHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    private static final Logger LOG = LoggerFactory.getLogger(MessageChannelHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket packet) throws Exception {
        String toUserId = packet.getToUserId();
        String message = packet.getMessage();
        Session fromSession = SessionUtil.getSession(ctx.channel()); // 消息发送方信
        Channel toChannel = SessionUtil.getChannel(toUserId);
        Session toSession = SessionUtil.getSession(toChannel);
        LOG.info("消息：" + fromSession.getNickName() + "-->" + toSession.getNickName() + ":" + message);

        // 转发消息至目标channel
        MessageResponsePacket responsePacket = new MessageResponsePacket();
        responsePacket.setMessage(message);
        responsePacket.setFromNickName(fromSession.getNickName());
        responsePacket.setFromUserId(fromSession.getUserId());

        toChannel.writeAndFlush(responsePacket);
    }

}

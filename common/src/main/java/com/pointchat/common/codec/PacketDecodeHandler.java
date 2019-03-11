package com.pointchat.common.codec;

import com.pointchat.common.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecodeHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        Packet packet = PacketCodeC.INSTANCE.decode(in);
        out.add(packet);
    }
}

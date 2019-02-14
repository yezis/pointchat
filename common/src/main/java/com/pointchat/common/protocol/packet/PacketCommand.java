package com.pointchat.common.protocol.packet;

public interface PacketCommand {

    /**
     * 身份验证请求 {@link AuthRequestPacket}
     */
    Byte AUTH_REQUEST = 1;

    /**
     * 身份验证响应 {@link AuthResponsePacket}
     */
    Byte AUTH_RESPONSE = 2;

}

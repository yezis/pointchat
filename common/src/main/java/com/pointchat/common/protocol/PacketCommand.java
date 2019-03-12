package com.pointchat.common.protocol;

public interface PacketCommand {

    /**
     * 身份验证请求 {@link AuthRequestPacket}
     */
    Byte AUTH_REQUEST = 1;

    /**
     * 身份验证响应 {@link AuthResponsePacket}
     */
    Byte AUTH_RESPONSE = 2;

    /**
     * 消息发送 {@link MessageRequestPacket}
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 消息接收 {@link MessageResponsePacket}
     */
    Byte MESSAGE_RESPONSE = 4;

}

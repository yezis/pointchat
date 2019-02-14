package com.pointchat.common.protocol.packet;

import lombok.Data;

/**
 * 身份验证请求数据包
 */
@Data
public class AuthRequestPacket extends Packet {

    private String username;

    private String token;

    @Override
    public Byte getCommand() {
        return PacketCommand.AUTH_REQUEST;
    }

}

package com.pointchat.common.protocol.packet;

import lombok.Data;

/**
 * 身份验证响应数据包
 */
@Data
public class AuthResponsePacket extends Packet {
    // 是否验证成功
    private boolean authSuccess;
    // 原因
    private String reason;

    @Override
    public Byte getCommand() {
        return PacketCommand.AUTH_RESPONSE;
    }

}

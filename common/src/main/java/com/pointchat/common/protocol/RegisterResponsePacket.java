package com.pointchat.common.protocol;

import lombok.Data;

/**
 * 身份验证响应数据包
 */
@Data
public class RegisterResponsePacket extends Packet {
    // 是否验证成功
    private boolean authSuccess;
    // 原因
    private String reason;

    private String userId;

    @Override
    public Byte getCommand() {
        return PacketCommand.REGISTER_RESPONSE;
    }

}

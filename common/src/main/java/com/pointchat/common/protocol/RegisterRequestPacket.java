package com.pointchat.common.protocol;

import lombok.Data;

/**
 * 身份验证请求数据包
 */
@Data
public class RegisterRequestPacket extends Packet {

    private String nickName;

    private String username;

    private String token;

    @Override
    public Byte getCommand() {
        return PacketCommand.REGISTER_REQUEST;
    }

}

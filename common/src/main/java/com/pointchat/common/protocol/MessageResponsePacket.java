package com.pointchat.common.protocol;

import lombok.Data;

@Data
public class MessageResponsePacket extends Packet {

    private String fromUserId;

    private String fromNickName;

    private String message;

    @Override
    public Byte getCommand() {
        return PacketCommand.MESSAGE_RESPONSE;
    }
}

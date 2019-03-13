package com.pointchat.common.protocol;

import lombok.Data;

@Data
public class MessageRequestPacket extends Packet {

    private String toUserId;

    private String message;

    @Override
    public Byte getCommand() {
        return PacketCommand.MESSAGE_REQUEST;
    }

}

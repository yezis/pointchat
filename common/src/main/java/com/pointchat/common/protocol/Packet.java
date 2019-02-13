package com.pointchat.common.protocol;

import lombok.Data;

/**
 * 数据协议包
 */
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 协议指令
     * @return
     */
    public abstract Byte getCommand();

}

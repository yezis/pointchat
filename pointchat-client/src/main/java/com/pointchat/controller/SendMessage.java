package com.pointchat.controller;

import com.pointchat.common.protocol.AuthRequestPacket;
import io.netty.channel.Channel;

public class SendMessage {

    public static void sendAuth(Channel channel){
        System.out.println("客户端发送数据");

        AuthRequestPacket packet = new AuthRequestPacket();
        packet.setUsername("wangyiming");
        packet.setToken("abcdefg");

        channel.writeAndFlush(packet);
    }

}

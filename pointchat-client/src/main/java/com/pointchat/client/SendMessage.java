package com.pointchat.client;

import com.pointchat.common.protocol.AuthRequestPacket;
import com.pointchat.common.protocol.MessageRequestPacket;
import com.pointchat.common.utils.LoginedUtil;
import io.netty.channel.Channel;

public class SendMessage {

    public static void authRequest(Channel channel, String username, String token){
        System.out.println("客户端发送数据");

        AuthRequestPacket packet = new AuthRequestPacket();
        packet.setUsername(username);
        packet.setToken(token);

        channel.writeAndFlush(packet);
    }

    public static void sendMessage(Channel channel, String message){
        if(LoginedUtil.checkLogined(channel)){
            System.out.println("发送消息：" + message);

            MessageRequestPacket packet = new MessageRequestPacket();
            packet.setMessage(message);

            channel.writeAndFlush(packet);
        }

    }

}

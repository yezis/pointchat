package com.pointchat.client;

import com.pointchat.common.protocol.RegisterRequestPacket;
import com.pointchat.common.protocol.MessageRequestPacket;
import com.pointchat.common.utils.LoginUtil;
import io.netty.channel.Channel;

public class SendMessage {

    public static void authRequest(Channel channel, String nickName, String username, String token){
        System.out.println("客户端发送数据");

        RegisterRequestPacket packet = new RegisterRequestPacket();
        packet.setNickName(nickName);
        packet.setUsername(username);
        packet.setToken(token);

        channel.writeAndFlush(packet);
    }

    public static void sendMessage(Channel channel, String message, String userId){
        if(LoginUtil.checkLogined(channel)){
            System.out.println("发送消息：" + message);

            MessageRequestPacket packet = new MessageRequestPacket();
            packet.setMessage(message);
            packet.setToUserId(userId);

            channel.writeAndFlush(packet);
        }

    }

}

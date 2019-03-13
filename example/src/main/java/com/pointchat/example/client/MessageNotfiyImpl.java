package com.pointchat.example.client;

import com.pointchat.client.MessageNotfiy;
import com.pointchat.client.SendMessage;
import com.pointchat.common.protocol.RegisterResponsePacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import java.util.Scanner;

public class MessageNotfiyImpl implements MessageNotfiy {

    @Override
    public void authResult(RegisterResponsePacket packet, final ChannelHandlerContext ctx) {
        if(packet.isAuthSuccess()){
            System.out.println("注册成功,userId：" + packet.getUserId());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.print("消息内容：");
                        Scanner cs = new Scanner(System.in);
                        String content = cs.nextLine();
                        String userId = content.split("@")[0];
                        String message = content.split("@")[1];
                        Channel channel = ctx.channel();
                        SendMessage.sendMessage(channel, message, userId);
                    }
                }
            }).start();
        }
        else{
            System.out.println("客户端未通过验证");
        }
    }

    @Override
    public void messageResponse(MessageResponsePacket packet, ChannelHandlerContext ctx) {
        System.out.println("新消息:" + packet.getFromNickName() + ":" + packet.getMessage());
    }

}

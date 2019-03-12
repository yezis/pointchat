package com.pointchat.example.client;

import com.pointchat.client.MessageNotfiy;
import com.pointchat.client.SendMessage;
import com.pointchat.common.protocol.AuthResponsePacket;
import com.pointchat.common.protocol.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import java.util.Scanner;

public class MessageNotfiyImpl implements MessageNotfiy {

    @Override
    public void authResult(AuthResponsePacket packet, ChannelHandlerContext ctx) {
        if(packet.isAuthSuccess()){
            System.out.println("客户端成功验证");
            while (true){
                System.out.print("消息内容：");
                Scanner cs = new Scanner(System.in);
                String message = cs.nextLine();
                SendMessage.sendMessage(ctx.channel(), message);
            }
        }
        else{
            System.out.println("客户端未通过验证");
        }
    }

    @Override
    public void messageResponse(MessageResponsePacket packet, ChannelHandlerContext ctx) {
        System.out.println("新消息:" + packet.getMessage());
    }

}

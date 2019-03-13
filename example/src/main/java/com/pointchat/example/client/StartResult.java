package com.pointchat.example.client;

import com.pointchat.client.ClientBootstrapConnectResult;
import com.pointchat.client.SendMessage;
import io.netty.channel.Channel;

public class StartResult implements ClientBootstrapConnectResult {

    @Override
    public void connectSuccess(Channel channel) {
        // 启动后，进行身份验证请求
        SendMessage.authRequest(channel, "nick","test", "123456");
    }

    @Override
    public void connectFail() {
        System.out.println("客户端启动失败");
        // 启动失败，进行重连尝试

    }
}

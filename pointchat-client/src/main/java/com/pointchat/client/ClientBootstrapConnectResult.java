package com.pointchat.client;

import io.netty.channel.Channel;

public interface ClientBootstrapConnectResult {

    public void connectSuccess(Channel channel);

    public void connectFail();

}

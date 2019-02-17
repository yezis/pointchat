package com.pointchat.client;

public class StartClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8808;
        ClientBootstrap bootstrap = new ClientBootstrap(host, port);
        bootstrap.start(new StartResult());
    }
}

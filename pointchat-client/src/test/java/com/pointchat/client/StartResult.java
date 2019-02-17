package com.pointchat.client;

public class StartResult implements ClientBootstrapCallBack {
    @Override
    public void isSuccess() {
        System.out.println("客户端启动成功");
    }

    @Override
    public void isFail() {
        System.out.println("客户端启动失败");
    }
}

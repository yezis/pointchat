package com.pointchat.common.utils;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {

    public static void markLogined(Channel channel){
        channel.attr(ChannelAttributes.LOGINED).set(true);
    }

    public static boolean checkLogined(Channel channel){
        Attribute<Boolean> loginedAttr = channel.attr(ChannelAttributes.LOGINED);
        return loginedAttr.get() != null;
    }

}

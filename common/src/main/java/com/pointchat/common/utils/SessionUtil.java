package com.pointchat.common.utils;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    private static final Map<String, Channel> userChannels = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel){
        userChannels.put(session.getUserId(), channel);
        channel.attr(ChannelAttributes.SESSION).set(session); // 保存用户信息至Channel，可供通过channel查找用户信息
    }

    public static void unBindSession(Channel channel){
        Session session = getSession(channel);
        userChannels.remove(session.getUserId());
        channel.attr(ChannelAttributes.SESSION).set(null);
    }

    public static Session getSession(Channel channel){
        return channel.attr(ChannelAttributes.SESSION).get();
    }

    public static Channel getChannel(String userId){
        return userChannels.get(userId);
    }


}

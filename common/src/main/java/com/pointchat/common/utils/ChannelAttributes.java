package com.pointchat.common.utils;

import io.netty.util.AttributeKey;

public interface ChannelAttributes {

    AttributeKey<Boolean> LOGINED = AttributeKey.newInstance("logined");

}

package com.pointchat.common.serialize;

public interface Serializer {

    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     */
    byte getSerializerAlgorithm();

    /**
     * java 对象转化为二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserializer(Class<T> clazz, byte[] bytes);

}

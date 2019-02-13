package com.pointchat.common.protocol;

import io.netty.buffer.ByteBuf;

/**
 * packet 解码器
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0X123456;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private PacketCodeC(){

    }

    /**
     * 解码
     * 魔数(4字节) | 版本号(1字节) | 序列化算法(1字节) |  请求指令(1字节) |  数据长度(4字节) |  数据("数据长度"字节) |
     * @param byteBuf
     * @return
     */
    public Packet decode(ByteBuf byteBuf){

        // 魔数
        int magicNumber = byteBuf.readInt();

        // 版本号
        byte version = byteBuf.readByte();

        // 序列化算法标示
        byte serializerAlgorithm = byteBuf.readByte();

        // 请求指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> clazz = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if(clazz != null && serializer != null){
            return serializer.deserializer(clazz, bytes);
        }

        return null;
    }


    public ByteBuf encode(ByteBuf byteBuf, Packet packet){

        Serializer serializer = getSerializer(SerializerAlgorithm.JSON);
        byte[] bytes = serializer.serialize(packet);

        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerAlgorithm.JSON);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }

    private Class<? extends Packet> getRequestType(byte command){
       /* if(command == LOGIN_REQUEST){
            return LoginRequestPacket.class;
        }
        else if(command == LOGIN_RESPONSE) {
            return LoginResponsePacket.class;
        }
        else if(command == MESSAGE_REQUEST){
            return MessageRequestPacket.class;
        }
        else if(command == MESSAGE_RESPONSE){
            return MessageResponsePacket.class;
        }*/

        return null;
    }

    private Serializer getSerializer(byte serializerAlgorithm){
        if(serializerAlgorithm == SerializerAlgorithm.JSON){
            return new JsonSerializer();
        }

        return null;
    }

}

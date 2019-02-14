package com.pointchat.common.protocol;

import com.pointchat.common.protocol.packet.AuthRequestPacket;
import com.pointchat.common.protocol.packet.AuthResponsePacket;
import com.pointchat.common.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;
import static com.pointchat.common.protocol.packet.PacketCommand.*;

/**
 * packet 解码器
 */
public class PacketCodeC {

    public static final int MAGIC_NUMBER = 0X123456;

    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final Map<Byte, Class<? extends Packet>> packetType = new HashMap<>();

    static {
        packetType.put(AUTH_REQUEST, AuthRequestPacket.class);
        packetType.put(AUTH_RESPONSE, AuthResponsePacket.class);
    }

    private PacketCodeC(){
    }

    /**
     * 解码
     *
     * 协议格式：
     * 魔数(4字节)|版本号(1字节)|序列化算法(1字节)|请求指令(1字节)|唯一码(1字节)|数据长度(4字节)|数据("数据长度"字节)
     *
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

        // 唯一码
        byte nonce = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bodyBytes = new byte[length];
        byteBuf.readBytes(bodyBytes);

        Class<? extends Packet> clazz = getRequestType(command);
        Serializer serializer = getSerializer(serializerAlgorithm);

        if(clazz != null && serializer != null){
            return serializer.deserializer(clazz, bodyBytes);
        }

        return null;
    }

    /**
     * 由数据包 编码为 ByteBuf
     * @param byteBuf
     * @param packet
     * @return
     */
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

    // 查找command对应的数据包类型
    private Class<? extends Packet> getRequestType(byte command){
        return packetType.get(command);
    }

    // 查找序列化方法
    private Serializer getSerializer(byte serializerAlgorithm){
        if(serializerAlgorithm == SerializerAlgorithm.JSON){
            return new JsonSerializer();
        }

        return null;
    }

}

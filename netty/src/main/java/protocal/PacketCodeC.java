package protocal;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import protocal.command.Command;
import protocal.request.LoginRequestPacket;
import protocal.request.MessageRequestPacket;
import protocal.response.LoginResponsePacket;
import protocal.response.MessageResponsePacket;
import protocal.serializer.Serializer;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

  public final static PacketCodeC INSTANCE = new PacketCodeC();

  private static Map<Byte,Class<? extends Packet>> clMap = new HashMap<>();
  static {
    clMap.put(Command.LOGIN_RE ,LoginRequestPacket.class);
    clMap.put(Command.LOGIN_RES ,LoginResponsePacket.class);
    clMap.put(Command.MESSAGE_REQUEST , MessageRequestPacket.class);
    clMap.put(Command.MESSAGE_RESPONSE , MessageResponsePacket.class);


  }

  //魔数，用于校验
  private static final int MAGIC_NUMBER = 0x12345678;

  public ByteBuf encode(ByteBufAllocator alloc,Packet packet) {
    //1. 创建buf对象 -- ioBuffer() 方法会返回适配 io 读写相关的内存
    ByteBuf buf = alloc.ioBuffer();

    //2. 序列化Java对象
    byte[] bytes = Serializer.DEFAULT.serialize(packet);

    //3.实际编码过程
    buf.writeInt(MAGIC_NUMBER);
    buf.writeByte(packet.getVersion());
    buf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    buf.writeByte(packet.getCommand());
    buf.writeInt(bytes.length);
    buf.writeBytes(bytes);
    return buf;
  }

  public Packet decode(ByteBuf byteBuf) {

    //跳过magic number
    byteBuf.skipBytes(4);
    // 跳过版本号
    byteBuf.skipBytes(1);
    //序列化算法标识
    byte serializeAlgorithm = byteBuf.readByte();

    // 指令
    byte command = byteBuf.readByte();
    //数据包长度
    int length = byteBuf.readInt();
    //读取数据
    byte[] bytes = new byte[length];
    //todo 注意要填充数据
    byteBuf.readBytes(bytes);

    //根据command转换成ClassType
    Class<? extends Packet> requestType = getRequestType(command);

    //获取序列化
    Serializer serializer = getSerializer(serializeAlgorithm);

    if (requestType != null && serializer != null) {
      //序列化对象
      return serializer.deserialize(requestType, bytes);
    }

    return null;

  }

  private Serializer getSerializer(byte serializeAlgorithm) {
    return Serializer.DEFAULT;
  }

  private Class<? extends Packet> getRequestType(byte command) {
      return clMap.get(command);
  }

}

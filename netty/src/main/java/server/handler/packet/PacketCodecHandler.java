package server.handler.packet;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import protocal.Packet;
import protocal.PacketCodeC;

import java.util.List;

/**
 * 编解码操作
 */
@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {
  public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

  private PacketCodecHandler(){}

  @Override
  protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
    list.add(PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc(),packet));
  }

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
    list.add(PacketCodeC.INSTANCE.decode(byteBuf));
  }
}

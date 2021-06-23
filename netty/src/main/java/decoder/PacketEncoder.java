package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protocal.Packet;
import protocal.PacketCodeC;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
  @Override
  protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
    //直接将Java对象写入
    PacketCodeC.INSTANCE.encode(out,packet);
  }
}

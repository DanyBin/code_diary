package decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protocal.PacketCodeC;

import java.util.List;

/**
 * 通过Netty将二进制转化为Java对象
 */
public class PacketDecoder extends ByteToMessageDecoder {
  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
    out.add(PacketCodeC.INSTANCE.decode(byteBuf));
  }
}

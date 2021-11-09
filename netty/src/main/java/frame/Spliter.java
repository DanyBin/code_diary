package frame;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import protocal.PacketCodeC;

/**
 * 自定义实现拆包 - 判断魔数
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

  public Spliter() {
    super(Integer.MAX_VALUE, 7, 4);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

    //判断数据包的开头，也就是魔数是否相同
    if (in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER) {
      ctx.channel().close();
      return null;
    }
    return super.decode(ctx,in);
  }
}

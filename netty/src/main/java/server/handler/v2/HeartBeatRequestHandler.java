package server.handler.v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.request.HeartBeatRequestPacket;
import protocal.response.HeartBeatResponsePacket;

/**
 * 心跳处理
 */
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
  public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

  private HeartBeatRequestHandler() {

  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
    ctx.writeAndFlush(new HeartBeatResponsePacket());
  }
}

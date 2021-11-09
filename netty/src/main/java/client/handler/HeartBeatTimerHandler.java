package client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocal.request.HeartBeatRequestPacket;

import java.util.concurrent.TimeUnit;

/**
 * 心跳发送-延时定时任务
 */
@ChannelHandler.Sharable
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
  private static final int HEARTBEAT_INTERVAL = 5;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
  }
  private void scheduleSendHeartBeat(ChannelHandlerContext ctx) {
    ctx.executor().schedule(() -> {
        ctx.writeAndFlush(new HeartBeatRequestPacket());
        scheduleSendHeartBeat(ctx);
    },HEARTBEAT_INTERVAL, TimeUnit.SECONDS);
  }
}

package server.handler.v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 空闲检测
 * 空闲检测指的是每隔一段时间，检测这段时间内是否有数据读写，简化一下，我们的服务端只需要检测一段时间内，是否收到过客户端发来的数据即可
 */
public class IMIdleStateHandler extends IdleStateHandler {
  private static final int READER_IDLE_TIME = 15;
  //如果 15 秒内没有读到数据，就表示连接假死。
  public IMIdleStateHandler() {
    super(READER_IDLE_TIME,0, 0, TimeUnit.MILLISECONDS);
  }


  @Override
  protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
    System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
    ctx.channel().close();
  }
}

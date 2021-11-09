package server.handler.v2;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.Packet;
import server.handler.LoginRequestHandlerV2;

import java.util.Map;

import static protocal.command.Command.LOGIN_RE;

public class IMHandler extends SimpleChannelInboundHandler<Packet> {

  public static final IMHandler INSTANCE = new IMHandler();

  //使用Map缓存所有的业务handler
  private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

  private IMHandler() {
    handlerMap.put(LOGIN_RE, LoginRequestHandlerV2.INSTANCE);
  }
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
    //根据不同的指令-获取具体的handler
    handlerMap.get(packet.getCommand()).channelRead(ctx,packet);
  }
}

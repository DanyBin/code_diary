package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.response.MessageResponsePacket;

import java.util.Date;

public class ClientMessageHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket responsePacket) throws Exception {
    System.out.println(new Date() + ": 收到服务端的信息：" + responsePacket.getMessage());
  }
}

package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.response.MessageResponsePacket;

public class MessageResponseHandlerV2 extends SimpleChannelInboundHandler<MessageResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
    String fromUserId = messageResponsePacket.getFromUserId();
    String fromUserName = messageResponsePacket.getFromUserName();
    String format = String.format("来自UserId=%s UserName=%s 的消息=%s", fromUserId, fromUserName, messageResponsePacket.getMessage());
    System.out.println(format);
  }
}

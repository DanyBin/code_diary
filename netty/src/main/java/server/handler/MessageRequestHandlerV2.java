package server.handler;

import domain.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.request.MessageRequestPacket;
import protocal.response.MessageResponsePacket;
import utils.SessionUtil;

public class MessageRequestHandlerV2 extends SimpleChannelInboundHandler<MessageRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
    // 1.拿到消息发送方的会话信息
    Session session = SessionUtil.getSession(ctx.channel());

    // 2.通过消息发送方的会话信息构造要发送的消息
    MessageResponsePacket  responsePacket = new MessageResponsePacket();
    responsePacket.setFromUserId(session.getUserId());
    responsePacket.setFromUserName(session.getUserName());
    responsePacket.setMessage(messageRequestPacket.getMessage());

    // 3.拿到消息接收方的 channel
    Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

    // 4.将消息发送给消息接收方
    if (null != toUserChannel && SessionUtil.hasLogin(toUserChannel)) {
      toUserChannel.pipeline().writeAndFlush(responsePacket);
    } else {
      System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
    }
  }
}

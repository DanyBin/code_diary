package server.handler;

import domain.Session;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.PacketCodeC;
import protocal.request.MessageRequestPacket;
import protocal.response.MessageResponsePacket;
import utils.SessionUtil;

import java.util.Date;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket requestPacket) throws Exception {
    //处理信息
    System.out.println(new Date() + ": 收到客户端信息：" + requestPacket.getMessage());
    MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
    messageResponsePacket.setMessage("服务端回复【"+requestPacket.getMessage()+"】");
    ctx.channel().writeAndFlush(messageResponsePacket);
  }
}

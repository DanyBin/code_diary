package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocal.request.QuitGroupRequestPacket;
import protocal.response.QuitGroupResponsePacket;
import utils.SessionUtil;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
    // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
    String groupId = quitGroupRequestPacket.getGroupId();
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    channelGroup.remove(ctx.channel());

    // 2. 构造退群响应发送给客户端
    QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
    quitGroupResponsePacket.setGroupId(groupId);
    quitGroupResponsePacket.setSuccess(true);
    ctx.channel().writeAndFlush(quitGroupResponsePacket);
  }
}

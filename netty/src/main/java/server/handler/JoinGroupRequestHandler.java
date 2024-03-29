package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocal.request.JoinGroupRequestPacket;
import protocal.response.JoinGroupResponsePacket;
import utils.SessionUtil;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
    // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
    String groupId = joinGroupRequestPacket.getGroupId();
    //增加channel
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    channelGroup.add(ctx.channel());

    // 2. 构造加群响应发送给客户端
    JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
    joinGroupResponsePacket.setSuccess(true);
    joinGroupResponsePacket.setGroupId(groupId);
    ctx.channel().writeAndFlush(joinGroupResponsePacket);
  }
}

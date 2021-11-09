package server.handler;

import domain.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocal.request.GroupMessageRequestPacket;
import protocal.response.GroupMessageResponsePacket;
import utils.SessionUtil;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) throws Exception {
    // 1.拿到 groupId 构造群聊消息的响应
    String toGroupId = requestPacket.getToGroupId();

    GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
    responsePacket.setFromGroupId(toGroupId);
    responsePacket.setMessage(requestPacket.getMessage());
    Session session = SessionUtil.getSession(ctx.channel());
    responsePacket.setFromUser(session);

    // 2. 拿到群聊对应的 channelGroup，写到每个客户端
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
    channelGroup.writeAndFlush(responsePacket);
  }
}

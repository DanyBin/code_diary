package server.handler;

import domain.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import protocal.request.ListGroupMembersRequestPacket;
import protocal.response.ListGroupMembersResponsePacket;
import utils.SessionUtil;

import java.util.List;
import java.util.stream.Collectors;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
    String groupId = listGroupMembersRequestPacket.getGroupId();
    ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
    List<Session> collect = channelGroup.stream()
        .map(SessionUtil::getSession)
        .collect(Collectors.toList());
    ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
    listGroupMembersResponsePacket.setGroupId(groupId);
    listGroupMembersResponsePacket.setSessionList(collect);
    ctx.channel().writeAndFlush(listGroupMembersResponsePacket);
  }
}

package server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import protocal.request.CreateGroupRequestPacket;
import protocal.response.CreateGroupResponsePacket;
import utils.IDUtil;
import utils.SessionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
    List<String> userIdList = createGroupRequestPacket.getUserIdList();

    List<String> userNameList = new ArrayList<>();

    // 1. 创建一个 channel 分组
    DefaultChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

    // 2. 筛选出待加入群聊的用户的 channel 和 userName
    for (String userId : userIdList) {
      Channel channel = SessionUtil.getChannel(userId);
      if (null != channel) {
        channelGroup.add(channel);
        userNameList.add(SessionUtil.getSession(channel).getUserName());
      }
    }

    // 3. 创建群聊创建结果的响应
    CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
    createGroupResponsePacket.setSuccess(true);
    createGroupResponsePacket.setUserNameList(userNameList);
    createGroupResponsePacket.setGroupId(String.valueOf(IDUtil.getId()));

    // 4. 给每个客户端发送拉群通知 -- 直接使用Group有问题
//    channelGroup.writeAndFlush(createGroupRequestPacket);

    for (String userId : userIdList) {
      Channel channel = SessionUtil.getChannel(userId);
      if (null != channel) {
        channel.pipeline().writeAndFlush(createGroupResponsePacket);
      }
    }

    //保存ChannelGroup
    SessionUtil.setGroupIdChannelGroupMap(createGroupResponsePacket.getGroupId(),channelGroup);

    System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
    System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());
  }
}

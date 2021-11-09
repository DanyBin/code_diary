package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.response.CreateGroupResponsePacket;

import java.util.Date;
import java.util.List;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
    if (createGroupResponsePacket.isSuccess()) {
      String groupId = createGroupResponsePacket.getGroupId();
      List<String> userNameList = createGroupResponsePacket.getUserNameList();
      System.out.println("群创建成功 id 为 ["+groupId+"] 群成员有:" + userNameList);
    } else {
      System.err.println(new Date() + ": 创建群组失败");
    }
  }
}

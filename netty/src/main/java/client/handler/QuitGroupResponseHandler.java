package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.response.QuitGroupResponsePacket;

public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
    String groupId = quitGroupResponsePacket.getGroupId();
    if (quitGroupResponsePacket.isSuccess()) {
      System.out.println("退群成功。 群Id = " + groupId);
    } else {
      System.err.println("退群失败");
    }
  }
}

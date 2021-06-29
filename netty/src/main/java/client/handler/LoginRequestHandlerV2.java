package client.handler;

import domain.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.request.LoginRequestPacket;
import protocal.response.LoginResponsePacket;
import utils.LoginUtil;
import utils.SessionUtil;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandlerV2 extends SimpleChannelInboundHandler<LoginResponsePacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) throws Exception {
    System.out.println(new Date() +" :客户端开始登录....");
    String userId = responsePacket.getUserId();
    if (responsePacket.isSuccess()) {
      //设置attr，标志位
      LoginUtil.markAsLogin(ctx.channel());
      SessionUtil.buildSession(new Session(userId,responsePacket.getUserName()),ctx.channel());
      System.out.println(new Date() + ": 客户端登录成功 userId->"+userId);
    } else {
      System.out.println(new Date() + ": 客户端登录失败，原因：" + responsePacket.getReason());
    }
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("客户端连接被关闭!");
  }
}

package server.handler;

import domain.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.request.LoginRequestPacket;
import protocal.response.LoginResponsePacket;
import utils.SessionUtil;
import utils.UserIdUtil;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 处理登录逻辑
 */
public class LoginRequestHandlerV2 extends SimpleChannelInboundHandler<LoginRequestPacket> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
    LoginResponsePacket responsePacket = new LoginResponsePacket();
    String userId = String.valueOf(UserIdUtil.getUserId());
    responsePacket.setUserId(userId);
    responsePacket.setUserName(loginRequestPacket.getUsername());
    responsePacket.setSuccess(true);
    SessionUtil.buildSession(new Session(userId,loginRequestPacket.getUsername()),ctx.channel());

    System.out.println(new Date() + "-> 用户 "+loginRequestPacket.getUsername() + "开始登录");
    //写回数据
    ctx.channel().writeAndFlush(responsePacket);
  }

  // 用户断线之后取消绑定
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    SessionUtil.unSession(ctx.channel());
  }
}

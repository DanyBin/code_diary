package server.handler;

import domain.Session;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.PacketCodeC;
import protocal.request.LoginRequestPacket;
import protocal.response.LoginResponsePacket;
import utils.SessionUtil;

import java.util.Date;
import java.util.Random;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 处理登录逻辑
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
    LoginResponsePacket responsePacket = new LoginResponsePacket();
    responsePacket.setVersion(loginRequestPacket.getVersion());
    if (loginRequestPacket.valid()) {
      //校验成功
      System.out.println("客户端登录成功");
      responsePacket.setSuccess(true);
    } else {
      //校验失败
      System.out.println("客户端登录失败");
      responsePacket.setReason("账号密码校验失败");
      responsePacket.setSuccess(false);
    }
    //写回数据
    ctx.channel().writeAndFlush(responsePacket);
  }

  // 用户断线之后取消绑定
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    SessionUtil.unSession(ctx.channel());
  }
}

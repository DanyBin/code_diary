package client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import protocal.PacketCodeC;
import protocal.request.LoginRequestPacket;
import protocal.response.LoginResponsePacket;
import utils.LoginUtil;

import java.util.Date;
import java.util.UUID;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println(new Date() + ": 客户端写出数据");

    //1. 获取数据
//    ByteBuf byteBuf = getByteBuf(ctx);
    LoginRequestPacket packet = new LoginRequestPacket();
    packet.setUserId(UUID.randomUUID().toString());
    packet.setUsername("flash");
    packet.setPassword("pwd");

    //2. 写数据
    ctx.channel().writeAndFlush(packet);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) throws Exception {
    System.out.println(new Date() +" :客户端开始登录....");
    if (responsePacket.isSuccess()) {
      //设置attr，标志位
      LoginUtil.markAsLogin(ctx.channel());
      System.out.println(new Date() + ": 客户端登录成功");
    } else {
      System.out.println(new Date() + ": 客户端登录失败，原因：" + responsePacket.getReason());
    }
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("客户端连接被关闭!");
  }
}

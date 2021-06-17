package client.adapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocal.Packet;
import protocal.PacketCodeC;
import protocal.request.LoginRequestPacket;
import protocal.response.LoginResponsePacket;
import protocal.response.MessageResponsePacket;
import utils.LoginUtil;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

public class FirstClientHandler extends ChannelInboundHandlerAdapter {

  /**
   * 客户端连接建立成功之后被调用，用于数据写出
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println(new Date() + ": 客户端写出数据");

    //1. 获取数据
//    ByteBuf byteBuf = getByteBuf(ctx);
    LoginRequestPacket packet = new LoginRequestPacket();
    packet.setUserId(UUID.randomUUID().toString());
    packet.setUsername("flash");
    packet.setPassword("pwd");

    //编码  -- ctx.alloc() 获取的就是与当前连接相关的 ByteBuf 分配器
    ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(),packet);

    //2. 写数据
    ctx.channel().writeAndFlush(encode);
  }

  /**
   * 用于数据读取
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println(new Date() +" :客户端开始登录....");
    ByteBuf byteBuf = (ByteBuf)msg;
//    System.out.println(String.format("%s 读取数据-> %s",new Date(),byteBuf.toString(Charset.forName("utf-8"))));

    Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);

    if (decode instanceof LoginResponsePacket) {
      LoginResponsePacket responsePacket = (LoginResponsePacket) decode;
      if (responsePacket.isSuccess()) {
        //设置attr，标志位
        LoginUtil.markAsLogin(ctx.channel());
        System.out.println(new Date() + ": 客户端登录成功");
      } else {
        System.out.println(new Date() + ": 客户端登录失败，原因：" + responsePacket.getReason());
      }
    } else if (decode instanceof MessageResponsePacket) {
      MessageResponsePacket responsePacket = (MessageResponsePacket) decode;
      System.out.println(new Date() + ": 收到服务端的信息：" + responsePacket.getMessage());
    }
  }

  private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
    //1. 获取二进制抽象 ByteBuf
    ByteBuf buffer = ctx
        //内存管理器
        .alloc()
        .buffer();

    //2. 准备数据-指定格式
    byte[] bytes = "hello world".getBytes(Charset.forName("utf-8"));

    //填充数据到 ByteBuf
    buffer.writeBytes(bytes);

    return buffer;
  }
}

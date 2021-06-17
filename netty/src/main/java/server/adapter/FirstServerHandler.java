package server.adapter;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import protocal.Packet;
import protocal.PacketCodeC;
import protocal.request.LoginRequestPacket;
import protocal.request.MessageRequestPacket;
import protocal.response.LoginResponsePacket;
import protocal.response.MessageResponsePacket;

import java.util.Date;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    ByteBuf buffer = ctx.alloc().buffer();
    byte[] bytes = "服务端主动发出数据".getBytes();
    buffer.writeBytes(bytes);
    ctx.channel().writeAndFlush(buffer);
  }

  /**
   * 用于数据读取/回复
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("客户端开始登录....");
    // 为什么 Netty 不直接把这个参数类型定义为 ByteBuf ？
    ByteBuf byteBuf = (ByteBuf) msg;

    //解码
    Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);

    LoginResponsePacket responsePacket = new LoginResponsePacket();

    //判断是否请求数据包
    if (decode instanceof LoginRequestPacket) {
      LoginRequestPacket requestPacket = (LoginRequestPacket) decode;
      responsePacket.setVersion(requestPacket.getVersion());
      if (requestPacket.valid()) {
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
      ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(),responsePacket);
      ctx.channel().writeAndFlush(encode);
    } else if (decode instanceof MessageRequestPacket) {
      //处理信息
      MessageRequestPacket requestPacket = (MessageRequestPacket)decode;
      System.out.println(new Date() + ": 收到客户端信息：" + requestPacket.getMessage());

      MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
      messageResponsePacket.setMessage("服务端回复【"+requestPacket.getMessage()+"】");
      ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
      ctx.channel().writeAndFlush(encode);
    }
  }


  public ByteBuf getByteBuf(ChannelHandlerContext ctx){
    ByteBuf buffer = ctx.alloc().buffer();
    byte[] bytes = String.format("%s : %s",new Date(),"服务端已接受到数据").getBytes();
    buffer.writeBytes(bytes);
    return buffer;
  }
}

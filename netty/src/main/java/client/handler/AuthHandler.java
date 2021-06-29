package client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import utils.LoginUtil;

public class AuthHandler extends ChannelInboundHandlerAdapter {

  /**
   * 该方法，能够读取所有数据
   * @param ctx
   * @param msg
   * @throws Exception
   */
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //校验是否通过
    if (LoginUtil.hasLogin(ctx.channel())) {
      //强制关闭链接
      ctx.channel().close();
    } else {
      ctx.pipeline().remove(this);
      super.channelRead(ctx, msg);
    }
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    if (LoginUtil.hasLogin(ctx.channel())) {
      System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
    } else {
      System.out.println("无登录验证，强制关闭连接!");
    }
  }
}

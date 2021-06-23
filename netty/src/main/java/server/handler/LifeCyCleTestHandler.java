package server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ChannelHandler的声明周期
 */
public class LifeCyCleTestHandler  extends ChannelInboundHandlerAdapter {

  /**
   * 将具体的Handler增加
   * @param ctx
   * @throws Exception
   */
  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("逻辑处理器被添加：handlerAdded()");
    super.handlerAdded(ctx);
  }

  /**
   * 获取从线程池中获取线程，绑定在channel上
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel 绑定到线程(NioEventLoop)：channelRegistered()");
    super.channelRegistered(ctx);
  }

  /**
   * Channel中「所有」的handler都添加到pipeline中。
   * 以及绑定了具体的线程
   * TCP 连接的建立
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel 准备就绪：channelActive()");
    super.channelActive(ctx);
  }


  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("channel 有数据可读：channelRead()");
    super.channelRead(ctx, msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel 某次数据读完：channelReadComplete()");
    super.channelReadComplete(ctx);
  }

  /**
   * TCP 连接的释放
   * @param ctx
   * @throws Exception
   */
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel 被关闭：channelInactive()");
    super.channelInactive(ctx);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channel 取消线程(NioEventLoop) 的绑定: channelUnregistered()");
    super.channelUnregistered(ctx);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    System.out.println("逻辑处理器被移除：handlerRemoved()");
    super.handlerRemoved(ctx);
  }
}


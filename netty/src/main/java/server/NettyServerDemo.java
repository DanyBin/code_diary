package server;

import decoder.PacketDecoder;
import decoder.PacketEncoder;
import frame.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import server.adapter.FirstServerHandler;
import server.handler.*;

import java.util.concurrent.ThreadFactory;

/**
 * NettySever的Demo
 */
public class NettyServerDemo {
  public static void main(String[] args) {
    //线程组 - 1 bossGroup表示监听端口，accept 新连接的线程组（接受新的连接Socket - 老板）
    NioEventLoopGroup bossGroup = new NioEventLoopGroup();
    //线程组 - 2 workerGroup表示处理每一条连接的数据读写的线程组 (负责读取数据&业务逻辑处理 - 工人)
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();

    ServerBootstrap severBootstrap = new ServerBootstrap();
    //参数配置
    severBootstrap
        .group(bossGroup,workerGroup) // 配置线程组
        .channel(NioServerSocketChannel.class) //服务端的 IO 模型为NIO
        .handler(new ChannelInitializer<NioServerSocketChannel>() {
          @Override
          protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
            System.out.println("服务端启动中");
          }
        })
        .option(ChannelOption.SO_BACKLOG,1024)
        .childOption(ChannelOption.SO_KEEPALIVE, true)//每条连接设置一些TCP底层相关的属性
        .childOption(ChannelOption.TCP_NODELAY, true)//服务端channel设置一些属性
        .childHandler(new ChannelInitializer<NioSocketChannel>() { //NioSocketChannel是Netty 对 NIO 类型的连接的抽象
          @Override
          protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
              //处理数据
           // nioSocketChannel.pipeline().addLast(new FirstServerHandler());
//            //处理读数据的逻辑链
//            nioSocketChannel.pipeline().addLast(new InBoundHandlerA());
//            nioSocketChannel.pipeline().addLast(new InBoundHandlerB());
//            nioSocketChannel.pipeline().addLast(new InBoundHandlerC());
//
//            //处理写数据的逻辑链
//            nioSocketChannel.pipeline().addLast(new OutBoundHandlerA());
//            nioSocketChannel.pipeline().addLast(new OutBoundHandlerB());
//            nioSocketChannel.pipeline().addLast(new OutBoundHandlerC());
//
            //拆包器使用
            //nioSocketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,7,4));

            nioSocketChannel.pipeline().addLast(new LifeCyCleTestHandler());

            //使用自定义的拆包
            nioSocketChannel.pipeline().addLast(new Spliter());
            nioSocketChannel.pipeline().addLast(new PacketDecoder());
            nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
            nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
            nioSocketChannel.pipeline().addLast(new PacketEncoder());
          }
        });
    bind(severBootstrap,8000);
  }

  //自动绑定递增端口的逻辑
  private static void bind(final ServerBootstrap serverBootstrap,final int port) {
    serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
      @Override
      public void operationComplete(Future<? super Void> future) throws Exception {
        if (future.isSuccess()) {
          System.out.println(String.format("端口[%d]绑定成功",port));
        } else {
          System.out.println(String.format("端口[%d]绑定失败",port));
          //递归调用
          bind(serverBootstrap,port+1);
        }
      }
    });
  }
}

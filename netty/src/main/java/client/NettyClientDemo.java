package client;

import client.adapter.FirstClientHandler;
import client.handler.ClientLoginRequestHandler;
import decoder.PacketDecoder;
import decoder.PacketEncoder;
import frame.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import protocal.PacketCodeC;
import protocal.request.MessageRequestPacket;
import server.handler.MessageRequestHandler;
import utils.LoginUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class NettyClientDemo {

  private static int MAX_RETRY = 5;

  public static void main(String[] args) {
    NioEventLoopGroup workerGroup = new NioEventLoopGroup();
    Bootstrap bootstrap = new Bootstrap();

    bootstrap
        //指定线程模型
        .group(workerGroup)
        //指定IO类型为Nio
        .channel(NioSocketChannel.class)
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.TCP_NODELAY, true)
        //IO处理逻辑
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            //socketChannel.pipeline().addLast(new FirstClientHandler());

            //拆包器使用
            socketChannel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,7,4));

            //使用自定义的拆包
            socketChannel.pipeline().addLast(new Spliter());
            socketChannel.pipeline().addLast(new PacketDecoder());
            socketChannel.pipeline().addLast(new ClientLoginRequestHandler());
            socketChannel.pipeline().addLast(new MessageRequestHandler());
            socketChannel.pipeline().addLast(new PacketEncoder());
          }
        });

    connect(bootstrap, "127.0.0.1", 8000, MAX_RETRY);
  }

  private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
    bootstrap.connect(host, port).addListener(future -> {
      if (future.isSuccess()) {
        System.out.println("连接成功");

        //todo 注意 通过该方法进行发送信息
        Channel channel = ((ChannelFuture) future).channel();
        startConsoleThread(channel);

      } else if (retry == 0) {
        System.out.println("连接失败");
      } else {
        int order = (MAX_RETRY - retry) + 1;
        //本次重连的间隔
        int delay = 1 << order;
        System.out.println(String.format("%s:连接第%d次重连..", new Date(), order));
        bootstrap.config()
            //线程组
            .group()
            //定时任务
            .schedule(() ->
                connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS
            );
      }
    });
  }

  //console发生信息
  private static void startConsoleThread(Channel channel) {
    new Thread(() -> {
      //支持中断。不中断是死循环-阻塞式-发送信息
      while (!Thread.interrupted()) {
        if (LoginUtil.hasLogin(channel)) {
          System.out.println("输入消息发送至服务端: ");
          Scanner sc = new Scanner(System.in);
          String line = sc.nextLine();

          MessageRequestPacket requestPacket = new MessageRequestPacket();
          requestPacket.setMessage(line);
          ByteBuf encode = PacketCodeC.INSTANCE.encode(channel.alloc(), requestPacket);
          channel.writeAndFlush(encode);
        }
      }
    }).start();
  }
}

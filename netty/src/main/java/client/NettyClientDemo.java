package client;

import client.console.ConsoleCommandManager;
import client.console.impl.LoginConsoleCommand;
import client.handler.*;
import decoder.PacketDecoder;
import decoder.PacketEncoder;
import frame.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import protocal.request.LoginRequestPacket;
import protocal.request.MessageRequestPacket;
import utils.SessionUtil;

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
            socketChannel.pipeline().addLast(new LoginRequestHandlerV2());
            socketChannel.pipeline().addLast(new MessageResponseHandlerV2());
            socketChannel.pipeline().addLast(new CreateGroupResponseHandler());
            socketChannel.pipeline().addLast(new JoinGroupResponseHandler());
            socketChannel.pipeline().addLast(new QuitGroupResponseHandler());
            socketChannel.pipeline().addLast(new ListGroupMembersResponseHandler());

            //新增用户权限验证功能
            socketChannel.pipeline().addLast(new AuthHandler());
            socketChannel.pipeline().addLast(new PacketEncoder());
          }
        });

    connect(bootstrap, "127.0.0.1", 8080, MAX_RETRY);
  }

  private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
    bootstrap.connect(host, port).addListener(future -> {
      if (future.isSuccess()) {
        System.out.println("连接成功");

        //todo 注意 通过该方法进行发送信息
        Channel channel = ((ChannelFuture) future).channel();
        startConsoleThreadV2(channel);

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
    Scanner sc = new Scanner(System.in);
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
    new Thread(() -> {
      //支持中断。不中断是死循环-阻塞式-发送信息
      while (!Thread.interrupted()) {
        if (!SessionUtil.hasLogin(channel)) {
          System.out.print("输入用户名登录: ");
          String userName = sc.nextLine();
          loginRequestPacket.setUsername(userName);
          loginRequestPacket.setPassword("pwd");
          channel.writeAndFlush(loginRequestPacket);
          waitForLoginResponse();
        } else {
          String toUserId = sc.next();
          String message = sc.next();
          channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
        }
      }
    }).start();
  }

  private static void waitForLoginResponse() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {
    }
  }

  private static void startConsoleThreadV2(Channel channel) {
    ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
    LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
    Scanner scanner = new Scanner(System.in);
    new Thread(() -> {
      while (!Thread.interrupted()) {
        if (!SessionUtil.hasLogin(channel)) {
          loginConsoleCommand.exec(scanner,channel);
          waitForLoginResponse();
        } else {
          consoleCommandManager.exec(scanner,channel);
        }
      }
    }).start();
  }

}

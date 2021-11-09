package client.console.impl;

import client.console.ConsoleCommand;
import io.netty.channel.Channel;
import protocal.request.LoginRequestPacket;

import java.util.Scanner;

//登录命令
public class LoginConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
    System.out.print("输入用户名登录: ");
    String userName = scanner.nextLine();
    loginRequestPacket.setUsername(userName);
    loginRequestPacket.setPassword("pwd");
    channel.writeAndFlush(loginRequestPacket);
  }
}

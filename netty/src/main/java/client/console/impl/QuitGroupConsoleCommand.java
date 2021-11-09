package client.console.impl;

import client.console.ConsoleCommand;
import io.netty.channel.Channel;
import protocal.request.JoinGroupRequestPacket;
import protocal.request.QuitGroupRequestPacket;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
    System.out.print("输入 groupId，退出群聊：");
    String groupId = scanner.next();
    requestPacket.setGroupId(groupId);
    channel.writeAndFlush(requestPacket);
  }
}

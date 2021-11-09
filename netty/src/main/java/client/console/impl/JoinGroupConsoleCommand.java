package client.console.impl;

import client.console.ConsoleCommand;
import io.netty.channel.Channel;
import protocal.request.JoinGroupRequestPacket;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
    System.out.print("输入 groupId，加入群聊：");
    String groupId = scanner.next();
    joinGroupRequestPacket.setGroupId(groupId);
    channel.writeAndFlush(joinGroupRequestPacket);
  }
}

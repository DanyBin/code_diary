package client.console.impl;

import client.console.ConsoleCommand;
import io.netty.channel.Channel;
import protocal.request.ListGroupMembersRequestPacket;

import java.util.Scanner;

public class ListGroupMembersConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();
    System.out.print("输入 groupId，获取群成员列表：");
    String groupId = scanner.next();
    listGroupMembersRequestPacket.setGroupId(groupId);
    channel.writeAndFlush(listGroupMembersRequestPacket);
  }
}

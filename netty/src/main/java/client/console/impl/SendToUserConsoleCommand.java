package client.console.impl;

import client.console.ConsoleCommand;
import io.netty.channel.Channel;
import protocal.request.MessageRequestPacket;

import java.util.Scanner;

public class SendToUserConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    String toUserId = scanner.next();
    String message = scanner.next();
    channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
  }
}

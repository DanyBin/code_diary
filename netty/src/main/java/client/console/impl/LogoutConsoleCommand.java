package client.console.impl;

import client.console.ConsoleCommand;
import domain.Session;
import io.netty.channel.Channel;
import utils.SessionUtil;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand {
  @Override
  public void exec(Scanner scanner, Channel channel) {
    channel.close();
  }
}

package client.console;

import client.console.impl.*;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

  private Map<String,ConsoleCommand> consoleCommandMap;

  public ConsoleCommandManager() {
    consoleCommandMap = new HashMap<>();
    consoleCommandMap.put("logout",new LogoutConsoleCommand());
    consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
    consoleCommandMap.put("createGroup",new CreateGroupConsoleCommand());
    consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
    consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    consoleCommandMap.put("quitGroup", new QuitGroupConsoleCommand());
  }
  @Override
  public void exec(Scanner scanner, Channel channel) {
    String command = scanner.next();
    ConsoleCommand consoleCommand = consoleCommandMap.get(command);
    if (null != consoleCommand) {
      consoleCommand.exec(scanner,channel);
    } else {
      System.err.println("无法识别[" + command + "]指令，请重新输入!");
    }
  }
}
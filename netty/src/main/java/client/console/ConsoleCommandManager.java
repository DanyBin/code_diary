package client.console;

import java.nio.channels.Channel;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {

  private Map<String,ConsoleCommand> consoleCommandMap;

  public ConsoleCommandManager() {
    consoleCommandMap = new HashMap<>();
//    consoleCommandMap.put("sendToUser",);

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

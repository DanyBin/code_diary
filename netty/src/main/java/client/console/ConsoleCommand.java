package client.console;

import java.nio.channels.Channel;
import java.util.Scanner;

public interface ConsoleCommand {
  void exec(Scanner scanner, Channel channel);
}

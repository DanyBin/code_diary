package protocal.command;

/**
 * 命令
 */
public interface Command {
  byte LOGIN_RE = 1;
  byte LOGIN_RES = 2;
  byte MESSAGE_REQUEST = 3;
  byte MESSAGE_RESPONSE = 4;
}

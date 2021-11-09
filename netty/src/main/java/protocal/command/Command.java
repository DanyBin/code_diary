package protocal.command;

/**
 * 命令
 */
public interface Command {
  byte LOGIN_RE = 1;
  byte LOGIN_RES = 2;
  byte MESSAGE_REQUEST = 3;
  byte MESSAGE_RESPONSE = 4;
  byte GROUP_REQUEST = 5;
  byte GROUP_RESPONSE = 6;
  byte JOIN_REQUEST = 7;
  byte JOIN_RESPONSE = 8;
  byte Quit_REQUEST = 9;
  byte Quit_RESPONSE = 10;
  byte GroupMembers_REQUEST = 11;
  byte GroupMembers_RESPONSE = 12;
  byte Group_MESSAGE_REQUEST = 13;
  byte Group_MESSAGE_RESPONSE = 14;
}

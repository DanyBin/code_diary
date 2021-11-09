package protocal.request;

import lombok.Data;
import protocal.Packet;
import protocal.command.Command;

//加入群聊命令
@Data
public class JoinGroupRequestPacket extends Packet {

  private String groupId;

  @Override
  public Byte getCommand() {
    return Command.JOIN_REQUEST;
  }
}

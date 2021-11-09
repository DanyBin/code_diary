package protocal.request;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.Group_MESSAGE_REQUEST;

@Data
public class GroupMessageRequestPacket extends Packet {

  private String toGroupId;
  private String message;

  @Override
  public Byte getCommand() {
    return Group_MESSAGE_REQUEST;
  }
}

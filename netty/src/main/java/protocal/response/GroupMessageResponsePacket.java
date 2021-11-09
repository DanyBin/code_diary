package protocal.response;

import domain.Session;
import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.Group_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {

  private String fromGroupId;
  private String message;
  private Session fromUser;

  @Override
  public Byte getCommand() {
    return Group_MESSAGE_RESPONSE;
  }
}

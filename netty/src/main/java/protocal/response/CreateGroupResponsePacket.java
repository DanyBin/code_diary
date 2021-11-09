package protocal.response;

import lombok.Data;
import protocal.Packet;

import java.util.List;

import static protocal.command.Command.GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {
  private boolean success;

  private String groupId;

  private List<String> userNameList;

  @Override
  public Byte getCommand() {
    return GROUP_RESPONSE;
  }
}

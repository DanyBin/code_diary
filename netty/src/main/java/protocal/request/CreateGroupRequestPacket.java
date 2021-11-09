package protocal.request;

import lombok.Data;
import protocal.Packet;

import java.util.List;

import static protocal.command.Command.GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {
  private List<String> userIdList;
  @Override
  public Byte getCommand() {
    return GROUP_REQUEST;
  }
}

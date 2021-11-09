package protocal.response;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.JOIN_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {
  private boolean success;
  private String groupId;
  private String reason;
  @Override
  public Byte getCommand() {
    return JOIN_RESPONSE;
  }
}

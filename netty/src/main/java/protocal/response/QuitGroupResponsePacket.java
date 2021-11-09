package protocal.response;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.Quit_RESPONSE;

@Data
public class QuitGroupResponsePacket extends Packet {
  private String groupId;
  private boolean success;
  @Override
  public Byte getCommand() {
    return Quit_RESPONSE;
  }
}

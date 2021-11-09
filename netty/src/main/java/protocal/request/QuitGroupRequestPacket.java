package protocal.request;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.Quit_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {

  private String groupId;

  @Override
  public Byte getCommand() {
    return Quit_REQUEST;
  }
}

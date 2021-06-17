package protocal.response;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.MESSAGE_RESPONSE;

@Data
public class MessageResponsePacket extends Packet {
  private String message;

  @Override
  public Byte getCommand() {
    return MESSAGE_RESPONSE;
  }
}

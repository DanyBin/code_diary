package protocal.request;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.MESSAGE_REQUEST;

@Data
public class MessageRequestPacket extends Packet {
  private String message;

  @Override
  public Byte getCommand() {
    return MESSAGE_REQUEST;
  }
}

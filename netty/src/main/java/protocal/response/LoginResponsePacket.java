package protocal.response;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.LOGIN_RES;

@Data
public class LoginResponsePacket extends Packet {

  private boolean success;

  private String reason;

  @Override
  public Byte getCommand() {
    return LOGIN_RES;
  }
}

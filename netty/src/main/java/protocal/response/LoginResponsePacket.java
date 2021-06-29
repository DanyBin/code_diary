package protocal.response;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.LOGIN_RES;

@Data
public class LoginResponsePacket extends Packet {

  private boolean success;

  private String reason;

  private String userId;

  private String userName;

  @Override
  public Byte getCommand() {
    return LOGIN_RES;
  }
}

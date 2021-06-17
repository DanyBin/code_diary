package protocal.request;

import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.LOGIN_RE;

@Data
public class LoginRequestPacket extends Packet {
  private String userId;

  private String username;

  private String password;

  @Override
  public Byte getCommand() {
    return LOGIN_RE;
  }

  public boolean valid() {
    return true;
  }
}

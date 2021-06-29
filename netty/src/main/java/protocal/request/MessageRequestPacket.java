package protocal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import protocal.Packet;

import static protocal.command.Command.MESSAGE_REQUEST;

@Data
@AllArgsConstructor
public class MessageRequestPacket extends Packet {
  //表示要发送给哪个用户
  private String toUserId;
  private String message;

  @Override
  public Byte getCommand() {
    return MESSAGE_REQUEST;
  }
}

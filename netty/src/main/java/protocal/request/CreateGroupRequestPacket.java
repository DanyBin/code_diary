package protocal.request;

import protocal.Packet;

import java.util.List;

public class CreateGroupRequestPacket extends Packet {
  private List<String> userIdList;
  @Override
  public Byte getCommand() {
    return null;
  }
}

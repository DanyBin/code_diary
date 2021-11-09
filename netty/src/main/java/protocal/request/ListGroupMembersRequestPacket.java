package protocal.request;

import domain.Session;
import lombok.Data;
import protocal.Packet;

import java.util.List;

import static protocal.command.Command.GroupMembers_REQUEST;

@Data
public class ListGroupMembersRequestPacket extends Packet {
  private String groupId;
  @Override
  public Byte getCommand() {
    return GroupMembers_REQUEST;
  }
}

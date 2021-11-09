package protocal.response;

import domain.Session;
import lombok.Data;
import protocal.Packet;

import java.util.List;

import static protocal.command.Command.GroupMembers_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {
  private String groupId;
  private List<Session> sessionList;
  @Override
  public Byte getCommand() {
    return GroupMembers_RESPONSE;
  }
}

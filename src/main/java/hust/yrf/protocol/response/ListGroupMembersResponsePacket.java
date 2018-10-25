package hust.yrf.protocol.response;

import hust.yrf.protocol.Packet;
import hust.yrf.session.Session;
import lombok.Data;

import java.util.List;

import static hust.yrf.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMembersResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}

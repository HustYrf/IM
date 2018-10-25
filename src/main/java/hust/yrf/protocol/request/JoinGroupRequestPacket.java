package hust.yrf.protocol.request;

import hust.yrf.protocol.Packet;
import lombok.Data;

import static hust.yrf.protocol.command.Command.JOIN_GROUP_REQUEST;

@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}

package hust.yrf.protocol.response;

import hust.yrf.protocol.Packet;
import hust.yrf.session.Session;
import lombok.Data;

import static hust.yrf.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}

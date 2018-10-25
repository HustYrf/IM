package hust.yrf.protocol.request;

import hust.yrf.protocol.Packet;
import lombok.Data;

import static hust.yrf.protocol.command.Command.QUIT_GROUP_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}

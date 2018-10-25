package hust.yrf.protocol.request;

import hust.yrf.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static hust.yrf.protocol.command.Command.GROUP_MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;
    private String messager;

    public GroupMessageRequestPacket(String toGroupId, String messager) {
        this.toGroupId = toGroupId;
        this.messager = messager;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}

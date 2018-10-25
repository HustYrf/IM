package hust.yrf.protocol.request;

import hust.yrf.protocol.Packet;
import lombok.Data;

import static hust.yrf.protocol.command.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}

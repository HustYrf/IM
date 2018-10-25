package hust.yrf.protocol.response;

import hust.yrf.protocol.Packet;
import lombok.Data;

import static hust.yrf.protocol.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}

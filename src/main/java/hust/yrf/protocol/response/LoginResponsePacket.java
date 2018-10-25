package hust.yrf.protocol.response;

import hust.yrf.protocol.Packet;
import lombok.Data;

import static hust.yrf.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private String userId;

    private String userName;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}

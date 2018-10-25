package hust.yrf.protocol.response;

import hust.yrf.protocol.Packet;

import static hust.yrf.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket  extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}

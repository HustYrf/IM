package hust.yrf.protocol.request;

import hust.yrf.protocol.Packet;

import static hust.yrf.protocol.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}

package hust.yrf.server.handler;

import hust.yrf.protocol.request.JoinGroupRequestPacket;
import hust.yrf.protocol.response.JoinGroupResponsePacket;
import hust.yrf.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    private JoinGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(joinGroupRequestPacket.getGroupId());
        channelGroup.add(channelHandlerContext.channel());

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();

        joinGroupResponsePacket.setSuccess(true);
        joinGroupResponsePacket.setGroupId(joinGroupRequestPacket.getGroupId());

        channelHandlerContext.writeAndFlush(joinGroupResponsePacket);
    }
}

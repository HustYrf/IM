package hust.yrf.server.handler;

import hust.yrf.protocol.request.QuitGroupRequestPacket;
import hust.yrf.protocol.response.QuitGroupResponsePacket;
import hust.yrf.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    public QuitGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(quitGroupRequestPacket.getGroupId());
        channelGroup.remove(channelHandlerContext.channel());

        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();

        quitGroupResponsePacket.setGroupId(quitGroupRequestPacket.getGroupId());
        quitGroupResponsePacket.setSuccess(true);

        channelHandlerContext.writeAndFlush(quitGroupResponsePacket);
    }
}

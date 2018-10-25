package hust.yrf.server.handler;

import hust.yrf.protocol.request.GroupMessageRequestPacket;
import hust.yrf.protocol.response.GroupMessageResponsePacket;
import hust.yrf.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    public static final GroupMessageRequestHandler INSTANCE = new GroupMessageRequestHandler();

    public GroupMessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupMessageRequestPacket groupMessageRequestPacket) throws Exception {
        GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
        responsePacket.setFromGroupId(groupMessageRequestPacket.getToGroupId());
        responsePacket.setFromUser(SessionUtil.getSession(channelHandlerContext.channel()));
        responsePacket.setMessage(groupMessageRequestPacket.getMessager());

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupMessageRequestPacket.getToGroupId());
        channelGroup.writeAndFlush(responsePacket);
    }
}

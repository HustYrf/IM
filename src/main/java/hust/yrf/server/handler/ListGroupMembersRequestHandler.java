package hust.yrf.server.handler;

import hust.yrf.protocol.request.ListGroupMembersRequestPacket;
import hust.yrf.protocol.response.ListGroupMembersResponsePacket;
import hust.yrf.session.Session;
import hust.yrf.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    public static final ListGroupMembersRequestHandler INSTANCE = new ListGroupMembersRequestHandler();

    public ListGroupMembersRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        String groupId = listGroupMembersRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        List<Session> sessionList = new ArrayList<>();
        for(Channel channel:channelGroup){
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);

        channelHandlerContext.writeAndFlush(responsePacket);

    }
}

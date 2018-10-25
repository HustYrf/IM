package hust.yrf.server.handler;

import hust.yrf.protocol.request.CreateGroupRequestPacket;
import hust.yrf.protocol.response.CreateGroupResponsePacket;
import hust.yrf.util.IDUtil;
import hust.yrf.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    public CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> userNameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        for (String userId : userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        }

        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUserNameList(userNameList);

        channelGroup.writeAndFlush(createGroupResponsePacket);
        System.out.print("群创建成功，id 为 " + createGroupResponsePacket.getGroupId() + ", ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());

        //保存群组相关的信息
        SessionUtil.bindChannelGroup(groupId, channelGroup);

    }
}

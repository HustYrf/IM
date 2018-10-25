package hust.yrf.server.handler;

import hust.yrf.protocol.request.MessageRequestPacket;
import hust.yrf.protocol.response.MessageResponsePacket;
import hust.yrf.session.Session;
import hust.yrf.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageRequestHandler INSTANCE = new MessageRequestHandler();

    public MessageRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) throws Exception {
        long begin = System.currentTimeMillis();

        Session session = SessionUtil.getSession(channelHandlerContext.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponsePacket).addListener(future -> {
               if(future.isDone()){
                   long end = System.currentTimeMillis();
                   System.out.println("消息花费时间为:" + (end - begin));
               }
            });
        }else{
            System.err.println("[" + session.getUserId() + "] 不在线，发送失败!");
        }
    }
}

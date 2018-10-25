package hust.yrf.server.handler;

import hust.yrf.protocol.request.LoginRequestPacket;
import hust.yrf.protocol.request.LogoutRequestPacket;
import hust.yrf.protocol.response.LogoutResponsePacket;
import hust.yrf.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    public static final LogoutRequestHandler INSTANCE = new LogoutRequestHandler();

    public LogoutRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LogoutRequestPacket LogoutRequestPacket) throws Exception {
        SessionUtil.unBindSession(channelHandlerContext.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        channelHandlerContext.writeAndFlush(logoutResponsePacket);
    }
}

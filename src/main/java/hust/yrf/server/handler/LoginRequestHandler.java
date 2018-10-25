package hust.yrf.server.handler;

import hust.yrf.protocol.request.LoginRequestPacket;
import hust.yrf.protocol.response.LoginResponsePacket;
import hust.yrf.session.Session;
import hust.yrf.util.IDUtil;
import hust.yrf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    public LoginRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUserName(loginRequestPacket.getUserName());
        if(valid(loginRequestPacket)){
            loginResponsePacket.setSuccess(true);
            String userID = IDUtil.randomId();
            loginResponsePacket.setUserId(userID);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
            SessionUtil.bindSession(new Session(userID,loginRequestPacket.getUserName()),channelHandlerContext.channel());
        }
        channelHandlerContext.writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}

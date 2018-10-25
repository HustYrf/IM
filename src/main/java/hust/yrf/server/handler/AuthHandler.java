package hust.yrf.server.handler;

import hust.yrf.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {
    public static final AuthHandler INSTANCE = new AuthHandler();

    public AuthHandler() {

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!SessionUtil.hasLogin(ctx.channel())){
            ctx.channel().close();
        }else{
            ctx.pipeline().remove(this);
            super.channelRead(ctx,msg);
        }
    }
}

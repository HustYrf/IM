package hust.yrf.server;

import hust.yrf.codec.PacketCodecHandler;
import hust.yrf.codec.Spliter;
import hust.yrf.handler.IMIdleStateHandler;
import hust.yrf.server.handler.AuthHandler;
import hust.yrf.server.handler.HeartBeatRequestHandler;
import hust.yrf.server.handler.IMHandler;
import hust.yrf.server.handler.LoginRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

public class NettyServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        // 空闲检测
                        ch.pipeline().addLast(new IMIdleStateHandler());
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(PacketCodecHandler.INSTANCE);
                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                        ch.pipeline().addLast(HeartBeatRequestHandler.INSTANCE);
                        ch.pipeline().addLast(AuthHandler.INSTANCE);
                        ch.pipeline().addLast(IMHandler.INSTANCE);
                    }
                });
        bind(bootstrap, PORT);
    }

    private static void bind(final ServerBootstrap bootstrap, final int PORT) {
        bootstrap.bind(PORT).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 端口[" + PORT + "]绑定成功!");
            } else {
                System.err.println("端口[" + PORT + "]绑定失败!");
            }
        });
    }
}

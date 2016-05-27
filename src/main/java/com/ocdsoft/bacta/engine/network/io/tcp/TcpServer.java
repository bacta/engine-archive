package com.ocdsoft.bacta.engine.network.io.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class TcpServer {
	private static final Logger LOGGER = LoggerFactory.getLogger(TcpServer.class);
	private ChannelInboundHandlerAdapter handler;

	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	private ServerBootstrap bootstrap = new ServerBootstrap();
	
	private int port;

	public TcpServer(ChannelInboundHandlerAdapter handler, int port) {
		this.port = port;
		this.handler = handler;
	}
	
    public final void start() {

        LOGGER.info("Starting TCP Server on port {}", port);

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    bootstrap.group(bossGroup, workerGroup)
                            .channel(NioServerSocketChannel.class)
                            .option(ChannelOption.SO_BACKLOG, 100)
                            .option(ChannelOption.TCP_NODELAY, true)
                            .childHandler(handler)
                            .childOption(ChannelOption.SO_KEEPALIVE, true);


                    LOGGER.info("TCP Server Running on port {}", port);

                    ChannelFuture f = bootstrap.bind(port).sync();
                    f.channel().closeFuture().sync();

                } catch (InterruptedException e) {
                    LOGGER.error(e.getMessage());
                } finally {
                    stop();
                }
            }
        }).start();
    }

	public void stop() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}

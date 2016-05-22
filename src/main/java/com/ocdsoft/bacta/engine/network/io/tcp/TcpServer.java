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


public abstract class TcpServer implements Runnable {
	private Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	private ChannelInboundHandlerAdapter handler;

	private EventLoopGroup bossGroup = new NioEventLoopGroup();
	private EventLoopGroup workerGroup = new NioEventLoopGroup();
	private ServerBootstrap bootstrap = new ServerBootstrap();
	
	private int port;

	public TcpServer(ChannelInboundHandlerAdapter handler, int port) {
		this.port = port;
		this.handler = handler;
	}
	
	@Override
    public final void run() {
		try {
			bootstrap.group(bossGroup, workerGroup)
			        .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .option(ChannelOption.TCP_NODELAY, true)
			        .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addFirst(new IdleStateHandler(0, 10, 0));
                            ch.pipeline().addLast(handler);
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);


			ChannelFuture f = bootstrap.bind(port).sync();
			f.channel().closeFuture().sync();

		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} finally {
			stop();
		}
    }

	public void stop() {
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
	}
}

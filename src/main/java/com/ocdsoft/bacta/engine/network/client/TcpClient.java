package com.ocdsoft.bacta.engine.network.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TcpClient {
	private final EventLoopGroup group = new NioEventLoopGroup();
	private final Bootstrap bootstrap = new Bootstrap();
	
	public TcpClient(ChannelInboundHandlerAdapter handler) {
		bootstrap.group(group)
		 .channel(NioSocketChannel.class)
		 .option(ChannelOption.TCP_NODELAY, true)
		 .handler(handler);
	}
	
	public Channel connect(String host, int port) throws InterruptedException {
		return bootstrap.connect(host, port).sync().channel();
	}

	public final void stop() {
		group.shutdownGracefully();
	}
}

package bacta.network.io.tcp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
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
			 .channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
			 .childHandler(handler);
			
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

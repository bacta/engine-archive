package bacta.network.io.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollDatagramChannel;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;

public final class UdpServer implements Runnable {
	protected Logger logger = LoggerFactory.getLogger(getClass().getSimpleName());
	
	private Bootstrap b = null;
	private ChannelInboundHandlerAdapter[] handlers;
	protected final InetAddress bindAddress;
    protected final int port;


	public UdpServer(InetAddress bindAddress, int port, ChannelInboundHandlerAdapter... handlers) {
		this.bindAddress = bindAddress;
        this.port = port;
		this.handlers = handlers;
	}
	
	@Override
    public void run() {

		try {

            b = new Bootstrap();

            if(Epoll.isAvailable()) {
                b.group(new EpollEventLoopGroup());
                b.channel(EpollDatagramChannel.class);
            } else {
                b.group(new NioEventLoopGroup());
                b.channel(NioDatagramChannel.class);
            }

            logger.debug("Starting on port: " + port);

	        b.option(ChannelOption.SO_RCVBUF, 768)
				.option(ChannelOption.SO_SNDBUF, 768)
				.option(ChannelOption.SO_BROADCAST, true)
                //.option(ChannelOption.ALLOCATOR, Unpooled.DEFAULT)
				.handler(new ChannelInitializer<NioDatagramChannel>() {

                    @Override
                    protected void initChannel(NioDatagramChannel ch)
                            throws Exception {

                        ch.pipeline().addLast(handlers);
                    }

                });

			Channel channel = b.bind(bindAddress, port).sync().channel();
					
			logger.debug("Running on port: " + port);

			channel.closeFuture().await();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			stop();
		}
    }

	public void stop()  {
		if (b != null)
			b.group().shutdownGracefully();
	}
}

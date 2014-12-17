package bacta.network.router;

public interface ByteMessageRouter<C, D> extends MessageRouter {
	void routeMessage(byte opcode, C client, D message);
}

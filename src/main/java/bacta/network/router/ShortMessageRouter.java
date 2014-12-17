package bacta.network.router;

public interface ShortMessageRouter<C, D> extends MessageRouter {
	void routeMessage(short opcode, C client, D message);
}

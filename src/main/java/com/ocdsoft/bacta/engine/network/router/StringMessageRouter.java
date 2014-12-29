package com.ocdsoft.bacta.engine.network.router;

public interface StringMessageRouter<C, D> extends MessageRouter {
	void routeMessage(String opcode, C client, D message);
}

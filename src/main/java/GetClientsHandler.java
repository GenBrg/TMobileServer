import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

class GetClientsHandler implements HttpHandler {
	private final Server server;

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("get clients!");
	}

	GetClientsHandler(Server server) {
		this.server = server;
	}
}

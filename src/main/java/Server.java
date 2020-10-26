import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	private final HttpServer server;
	private final Map<String, User> loggedInUsers = new HashMap<>();
	private final Database database;

	public Server(String hostname, int port, int backlog) throws IOException {
		server = HttpServer.create(new InetSocketAddress(hostname, port), backlog);
		database = new Database();

		// ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

		server.createContext("/login", new LoginHandler(this));
		server.createContext("/register", new RegisterHandler(this));
		server.createContext("/configuration", new ConfigurationHandler(this));
		server.createContext("/get-clients", new GetClientsHandler(this));
		// server.setExecutor(threadPoolExecutor);
	}

	public void start() {
		server.start();
	}

	public static void main(String[] args) throws IOException {
		
		Server server = new Server("localhost", 8001, 0);
		server.start();
	}
}

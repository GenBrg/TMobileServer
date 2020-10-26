import com.google.gson.Gson;
import com.sun.net.httpserver.HttpServer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
	private final HttpServer server;
	private final Map<String, User> loggedInUsers = new HashMap<>();
	private final Database database;

	public class ServerConfig {
		String hostname;
		int port;
		int backlog;
	}

	public Server(String serverConfigFile, String databaseConfigFile) throws IOException, SQLException, ClassNotFoundException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader(serverConfigFile));
		ServerConfig config = gson.fromJson(br, ServerConfig.class);

		server = HttpServer.create(new InetSocketAddress(config.hostname, config.port), config.backlog);
		database = new Database(databaseConfigFile);

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

	public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
		Server server = new Server("src/main/java/resources/server_config.json",
				"src/main/java/resources/database_config.json");
		server.start();
	}
}

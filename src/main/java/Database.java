import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Database {
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private Connection connection;

    public class DatabaseConfig {
        public String url;
        public String username;
        public String password;
    }

    Database(String configFile) throws FileNotFoundException, ClassNotFoundException, SQLException {
        Gson gson = new Gson();
        BufferedReader br = new BufferedReader(new FileReader(configFile));
        DatabaseConfig config = gson.fromJson(br, DatabaseConfig.class);

        Class.forName(JDBC_DRIVER);
        connection = DriverManager.getConnection(config.url, config.username, config.password);
    }


}

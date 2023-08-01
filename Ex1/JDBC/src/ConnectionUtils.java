import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class ConnectionUtils {
 
    private static final String hostName = "localhost";
    private static final String dbName = "test";
    private static final String userName = "root";
    private static final String password = "Timelord0war";
    // jdbc:mysql://hostname:port/dbname
    private static final String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
 
    public static Connection openConnection() throws SQLException {
        // 1. Open connection
        return DriverManager.getConnection(connectionURL, userName, password);
    }
}
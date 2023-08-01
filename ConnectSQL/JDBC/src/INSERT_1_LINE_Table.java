import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;

public class INSERT_1_LINE_Table {

    private static final String dbName = "Test";
    private static final String userName = "root";
    private static final String password = "Timelord0war";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + dbName;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 1. Open connection
        Connection con = DriverManager.getConnection(DB_URL, userName, password);
 
        // 2. Create Statement
        Statement st = con.createStatement();

        // 3. Execute query
        String sqlInsert = "INSERT INTO user(username, password1, createdDate) VALUE('THEANH', '12352', now());";
        int numberRowsAffected = st.executeUpdate(sqlInsert);
        if (numberRowsAffected == 0) {
            System.out.println("insertion failed");
        } else {
            System.out.println("inserted successfully : " + numberRowsAffected);
        }
        st.close();
         
        // 4. Close connection
        con.close();
    }
}

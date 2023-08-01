import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Create_DB{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASS = "Timelord0war";
    public static void main(String[] args){
        // 1. Open connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        // 2. Create Statement
        Statement stmt = conn.createStatement();){
            // 3.Create Database
            String sql = "CREATE DATABASE Test";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
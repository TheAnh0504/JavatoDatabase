import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class executeUpdate {
    public static void main(String[] args) throws SQLException{
        try(
            Connection con = ConnectionUtils.openConnection();
            Statement st = con.createStatement();
        ) {
            //Insert
            String sqlInsert = "INSERT INTO user(username, password, createdDate) VALUE('user3', '1234', now());";
            int numberRowsAffected = st.executeUpdate(sqlInsert);
            System.out.println("Affected rows after inserted: " + numberRowsAffected);
 
            // Update
            String sqlUpdate = "UPDATE user SET password='123456' WHERE id=1";
            numberRowsAffected = st.executeUpdate(sqlUpdate);
            System.out.println("Affected rows after updated: " + numberRowsAffected);
 
            // Delete
            String sqlDelte = "DELETE FROM user WHERE id=1";
            numberRowsAffected = st.executeUpdate(sqlDelte);
            System.out.println("Affected rows after deleted: " + numberRowsAffected);
        }
    }
}


import java.sql.*;

public class DbConnection {
    Connection con = null;
    public static Connection ConnectionDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:LoginAccountDB.db");
            System.out.println("Connection Suceeded");
            return con;
        } catch (Exception e) {
            System.out.println("Connection failed"+e);
            return null;
        }
    }
}
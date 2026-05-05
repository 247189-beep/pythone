import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableLogin {

    public static void getUserData(String username) {
        String dbUrl = "jdbc:mysql://localhost:3306/mydb";
        
        try (Connection conn = DriverManager.getConnection(dbUrl, "root", "password");
             Statement stmt = conn.createStatement()) {

            // SECURITY ISSUE: String concatenation used directly in the SQL query
            String query = "SELECT * FROM users WHERE username = '" + username + "'";
            
            System.out.println("Executing Query: " + query);
            
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("User found: " + rs.getString("username"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
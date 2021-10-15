package dbconnection;

import java.sql.*;

public class DatabaseTesting {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/skillo_db";
    //Database credentials
    static final String USER = "root";
    static final String PASS = "123456789";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            //STEP 3: Open connection
            System.out.println("Connecting to the database.......");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // STEP 4: Execute a query
            System.out.println("Creating statement.....");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT username, email, password FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("User: " + rs.getString("username"));
                System.out.print(", Email: " + rs.getString("email"));
                System.out.println(", Password: " + rs.getString("password"));
            }
            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt !=null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn !=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("THE END!");
    }
}

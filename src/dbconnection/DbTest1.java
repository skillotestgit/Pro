package dbconnection;

import com.google.common.hash.HashingOutputStream;

import java.sql.*;

public class DbTest1 {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/skillo_db";
    //Database credentials
    static final String USER = "root";
    static final String PASS = "123456789";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to the database.......");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            //Starting Insert procedure
            System.out.println("Creating statement.....");
            stmt = conn.createStatement();
            String insStat = "insert into skillo_db.user (iduser,username, email, password) values (1, 'UserNameHere', 'emailadress@skillo-bg.www', 1234567);";
            stmt.executeUpdate(insStat);
            System.out.println("Record is inserted in the table successfully. Thanks!");
            //Select procedure to check what is in DB
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
            rs.last();
            rs.deleteRow();


        } catch (SQLException excep) {
            excep.printStackTrace();
        } catch (Exception excep) {
            excep.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    conn.close();
            } catch (SQLException se) {}
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Please check it in the MySQL Table......... ……..");
    }
}






package dbconnection;

import java.sql.*;

public class DbTest2 {
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
            stmt.executeUpdate("CREATE TABLE employee (idemployee int(10), name varchar(50))");
            stmt.executeUpdate("insert into employee (idemployee, name) values(1, 'Vasko')");
            stmt.executeUpdate("insert into employee (idemployee, name) values(1, 'Vasko')");
            stmt.executeUpdate("insert into employee (idemployee, name) values(1, 'Vasko')");

            String sql;
            sql = "select * from skillo_db.employee";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("Number in the listing: " + rs.getString("idemployee"));
                System.out.println("        Name: " + rs.getString("name"));
            }
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






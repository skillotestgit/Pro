package dbconnection;
import java.sql.*;

public class DbTest3 {
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
            stmt.executeUpdate("CREATE TABLE factory (idfactory int(10), name varchar(50))");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(1, 'Mercedes')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(2, 'BMW')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(3, 'Audi')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(4, 'Dacia')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(5, 'Lada')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(6, 'Zaz')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(7, 'Opel')");
            stmt.executeUpdate("insert into factory  (idfactory, name) values(8, 'Mini')");
            String sql;
            sql = "select * from skillo_db.factory";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("Number in the listing: " + rs.getString("idfactory"));
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





